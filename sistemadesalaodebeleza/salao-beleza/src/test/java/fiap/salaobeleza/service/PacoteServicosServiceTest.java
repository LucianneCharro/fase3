package fiap.salaobeleza.service;

import fiap.salaobeleza.model.PacoteServicos;
import fiap.salaobeleza.model.Especialidades;
import fiap.salaobeleza.repository.PacoteServicosRepository;
import fiap.salaobeleza.repository.EspecialidadeRepository;
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
    private EspecialidadeRepository servicoRepository;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Especialidades servicoMock = new Especialidades();
        servicoMock.setId(1L);
        lenient().when(servicoRepository.findById(anyLong())).thenReturn(Optional.of(servicoMock));
        pacoteServicosService = new PacoteServicosService(pacoteServicosRepository, servicoRepository);
    }
    @Test
    public void createPacoteServicos_ValidPacoteServicos_ReturnsPacoteServicos() {
        PacoteServicos pacoteServicos = new PacoteServicos();
        pacoteServicos.setId(1L);
        pacoteServicos.setPreco(10.00);
        pacoteServicos.setNome("Pacote 1");
        HashSet<Especialidades> servicos = new HashSet<>();
        Especialidades servico = new Especialidades();
        servico.setId(1L);
        servicos.add(servico);
        pacoteServicos.setEspecialidades(servicos);

        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));
        when(pacoteServicosRepository.save(any(PacoteServicos.class))).thenReturn(pacoteServicos);

        PacoteServicos createdPacoteServicos = pacoteServicosService.createPacoteServicos(pacoteServicos);

        assertNotNull(createdPacoteServicos);
        assertEquals("Pacote 1", createdPacoteServicos.getNome());
        verify(servicoRepository).findById(1L);
        verify(pacoteServicosRepository).save(any(PacoteServicos.class));
    }

    @Test
    public void getPacoteServicosById_ExistingId_ReturnsPacoteServicos() {
        PacoteServicos pacoteServicos = new PacoteServicos();
        pacoteServicos.setId(1L);

        when(pacoteServicosRepository.findById(1L)).thenReturn(Optional.of(pacoteServicos));

        PacoteServicos foundPacoteServicos = pacoteServicosService.getPacoteServicosById(1L);

        assertEquals(1L, foundPacoteServicos.getId());
    }
}
