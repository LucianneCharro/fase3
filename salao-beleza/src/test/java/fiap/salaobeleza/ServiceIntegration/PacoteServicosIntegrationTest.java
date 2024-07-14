package fiap.salaobeleza.ServiceIntegration;

import fiap.salaobeleza.model.PacoteServicos;
import fiap.salaobeleza.model.Especialidades;
import fiap.salaobeleza.repository.EspecialidadeRepository;
import fiap.salaobeleza.service.PacoteServicosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class PacoteServicosIntegrationTest {

    @Autowired
    private PacoteServicosService pacoteServicosService;

    @Autowired
    private EspecialidadeRepository servicoRepository;

    @BeforeEach
    public void setup() {
        Especialidades servico = new Especialidades();
        servico.setId(1L);
        servico.setDescricao("Serviço Teste");
        servico.setPreco(100.0);
        servicoRepository.save(servico);
    }

    @Test
    public void testCreatePacoteServicos() {
        Especialidades existingServico = servicoRepository.findById(1L).orElseThrow();
        PacoteServicos pacoteServicos = new PacoteServicos();
        pacoteServicos.setNome("Pacote Teste");
        HashSet<Especialidades> servicos = new HashSet<>();
        servicos.add(existingServico);
        pacoteServicos.setEspecialidades(servicos);
        PacoteServicos savedPacoteServicos = pacoteServicosService.createPacoteServicos(pacoteServicos);

        assertNotNull(savedPacoteServicos, "The saved PacoteServicos should not be null");

        assertEquals("Pacote Teste", savedPacoteServicos.getNome(), "The name of the saved PacoteServicos does not match the expected value");

        assertEquals(1, savedPacoteServicos.getEspecialidades().size(), "The number of services in the saved PacoteServicos does not match the expected value");
    }
}