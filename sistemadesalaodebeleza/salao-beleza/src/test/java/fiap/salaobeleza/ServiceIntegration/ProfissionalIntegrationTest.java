package fiap.salaobeleza.ServiceIntegration;

import fiap.salaobeleza.model.Profissional;
import fiap.salaobeleza.repository.ProfissionalRepository;
import fiap.salaobeleza.service.ProfissionalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProfissionalIntegrationTest {

    @Autowired
    private ProfissionalService profissionalService;

    @MockBean
    private ProfissionalRepository profissionalRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Profissional profissionalMock = new Profissional();
        profissionalMock.setId(1L);
        profissionalMock.setNome("Profissional Teste");
        // Configura o mock para retornar o profissionalMock quando findById(1L) for chamado
        when(profissionalRepository.findById(1L)).thenReturn(Optional.of(profissionalMock));
    }

    @Test
    public void testBuscarProfissionalPorId() {
        // Ação: busca o profissional por ID
        Profissional profissionalEncontrado = profissionalService.getProfissionalById(1L);

        // Verificação: o profissional foi encontrado corretamente
        assertNotNull(profissionalEncontrado);
        assertEquals("Profissional Teste", profissionalEncontrado.getNome());
    }
}