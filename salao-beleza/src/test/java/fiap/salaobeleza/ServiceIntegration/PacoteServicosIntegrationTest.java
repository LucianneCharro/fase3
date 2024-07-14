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
@ActiveProfiles("test") // Ensure you're using application-test.properties configurations
public class PacoteServicosIntegrationTest {

    @Autowired
    private PacoteServicosService pacoteServicosService;

    @Autowired
    private EspecialidadeRepository servicoRepository; // Use real repository

    @BeforeEach
    public void setup() {
        // Create and save a Servico entity
        Especialidades servico = new Especialidades();
        servico.setId(1L); // Set the ID expected by the test
        servico.setDescricao("Serviço Teste");
        servico.setPreco(100.0);
        // Save the servico database
        servicoRepository.save(servico);
    }

    @Test
    public void testCreatePacoteServicos() {
        // Ensure the Servico exists in the database
        Especialidades existingServico = servicoRepository.findById(1L).orElseThrow();

        // Creation of a service package for testing
        PacoteServicos pacoteServicos = new PacoteServicos();
        pacoteServicos.setNome("Pacote Teste");
        HashSet<Especialidades> servicos = new HashSet<>();
        servicos.add(existingServico); // Add the existingServico to the collection to pass validation
        pacoteServicos.setEspecialidades(servicos);
        // Action: Save the PacoteServicos instance to the database
        PacoteServicos savedPacoteServicos = pacoteServicosService.createPacoteServicos(pacoteServicos);

        // Assert that the saved instance is not null
        assertNotNull(savedPacoteServicos, "The saved PacoteServicos should not be null");

        // Assert that the saved instance has the expected name
        assertEquals("Pacote Teste", savedPacoteServicos.getNome(), "The name of the saved PacoteServicos does not match the expected value");

        // Assert that the saved instance contains the expected number of services
        assertEquals(1, savedPacoteServicos.getEspecialidades().size(), "The number of services in the saved PacoteServicos does not match the expected value");
    }
}