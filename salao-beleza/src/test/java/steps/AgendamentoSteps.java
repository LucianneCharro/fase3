package steps;

import fiap.salaobeleza.model.Agendamento;
import fiap.salaobeleza.model.Cliente;
import fiap.salaobeleza.model.Especialidades;
import fiap.salaobeleza.repository.ClienteRepository;
import fiap.salaobeleza.repository.EspecialidadeRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgendamentoSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    private Especialidades buscarEspecialidadePorId(Long id) {
        return especialidadeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado"));
    }

    private Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    @Given("que eu quero criar um novo agendamento com os dados necessários")
    public void que_eu_quero_criar_um_novo_agendamento_com_os_dados_necessarios() {
    }

    @When("eu faço uma requisição POST para {string} com os dados do agendamento")
    public void eu_faco_uma_requisicao_POST_para_com_os_dados_do_agendamento(String url) {
        Agendamento agendamento = new Agendamento();

        Especialidades especialidade = buscarEspecialidadePorId(1L);
        Cliente cliente = buscarClientePorId(1L);

        agendamento.setEspecialidade(especialidade);
        agendamento.setDataHora(LocalDateTime.parse("2024-07-05T14:00:00"));
        agendamento.setCliente(cliente);

        response = restTemplate.postForEntity(url, agendamento, String.class);
    }

    @Then("o agendamento deve ser criado e a resposta deve ser os detalhes do agendamento criado")
    public void o_agendamento_deve_ser_criado_e_a_resposta_deve_ser_os_detalhes_do_agendamento_criado() {
    }
}