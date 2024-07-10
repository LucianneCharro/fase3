package fiap.salaobeleza.performance;

import fiap.salaobeleza.model.Agendamento;
import fiap.salaobeleza.model.Cliente;
import fiap.salaobeleza.model.Servico;
import fiap.salaobeleza.repository.ClienteRepository;
import fiap.salaobeleza.repository.ServicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgendamentoSimultaneo {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    @Test
    public void testServicoRepositoryIsNotNull() {
        assertNotNull(servicoRepository);
    }
    private static final TestRestTemplate restTemplate = new TestRestTemplate();

    public void executeTasks() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Callable<ResponseEntity<String>>> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            Servico servico = servicoRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado"));
            Cliente cliente = clienteRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

            Agendamento agendamento = new Agendamento();
            agendamento.setServico(servico);
            agendamento.setCliente(cliente);
            agendamento.setDataHora(LocalDateTime.of(2023, Month.OCTOBER, 5, 14, 0));

            tasks.add(new AgendamentoTask("http://localhost:8080/agendamentos", agendamento));
        }

        List<Future<ResponseEntity<String>>> futures = executor.invokeAll(tasks);

        for (Future<ResponseEntity<String>> future : futures) {
            ResponseEntity<String> response = future.get();
            System.out.println(response.getBody());
        }

        executor.shutdown();
    }

    static class AgendamentoTask implements Callable<ResponseEntity<String>> {
        private final String url;
        private final Object request;

        public AgendamentoTask(String url, Object request) {
            this.url = url;
            this.request = request;
        }

        @Override
        public ResponseEntity<String> call() throws Exception {
            return restTemplate.postForEntity(url, request, String.class);
        }
    }
}
