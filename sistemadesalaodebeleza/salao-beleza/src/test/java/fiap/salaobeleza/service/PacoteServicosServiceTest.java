package fiap.salaobeleza.service;

import fiap.salaobeleza.model.PacoteServicos;
import fiap.salaobeleza.model.Servico;
import fiap.salaobeleza.repository.PacoteServicosRepository;
import fiap.salaobeleza.repository.ServicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PacoteServicosServiceTest {

    @InjectMocks
    private PacoteServicosService pacoteServicosService;

    @Mock
    private PacoteServicosRepository pacoteServicosRepository;

    @Mock
    private ServicoRepository servicoRepository;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Servico servicoMock = new Servico();
        servicoMock.setId(1L);
        // Ensure the mock returns the expected Servico object for any Long type argument
        // This makes the mock more flexible and ensures it returns the mock Servico for any ID
        lenient().when(servicoRepository.findById(anyLong())).thenReturn(Optional.of(servicoMock));
        pacoteServicosService = new PacoteServicosService(pacoteServicosRepository, servicoRepository);
    }
    @Test
    public void createPacoteServicos_ValidPacoteServicos_ReturnsPacoteServicos() {
        // Preparação
        PacoteServicos pacoteServicos = new PacoteServicos();
        pacoteServicos.setId(1L);
        pacoteServicos.setPreco(10.00);
        pacoteServicos.setNome("Pacote 1");
        HashSet<Servico> servicos = new HashSet<>();
        Servico servico = new Servico();
        servico.setId(1L);
        servicos.add(servico);
        pacoteServicos.setServicos(servicos);

        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));
        when(pacoteServicosRepository.save(any(PacoteServicos.class))).thenReturn(pacoteServicos);

        // Ação
        PacoteServicos createdPacoteServicos = pacoteServicosService.createPacoteServicos(pacoteServicos);

        // Verificação
        assertNotNull(createdPacoteServicos);
        assertEquals("Pacote 1", createdPacoteServicos.getNome());
        verify(servicoRepository).findById(1L);
        verify(pacoteServicosRepository).save(any(PacoteServicos.class));
    }

    @Test
    public void getPacoteServicosById_ExistingId_ReturnsPacoteServicos() {
        // Preparação
        PacoteServicos pacoteServicos = new PacoteServicos();
        pacoteServicos.setId(1L);

        when(pacoteServicosRepository.findById(1L)).thenReturn(Optional.of(pacoteServicos));

        PacoteServicos foundPacoteServicos = pacoteServicosService.getPacoteServicosById(1L);

        assertEquals(1L, foundPacoteServicos.getId());
    }
}
