package fiap.salaobeleza.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import fiap.salaobeleza.model.Profissional;
import fiap.salaobeleza.model.Servico;
import fiap.salaobeleza.repository.ProfissionalRepository;
import fiap.salaobeleza.repository.ServicoRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProfissionalServiceTest {

    @InjectMocks
    private ProfissionalService profissionalService;

    @Mock
    private ProfissionalRepository profissionalRepository;

    @Mock
    private ServicoRepository servicoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        Servico servicoMock = new Servico();
        servicoMock.setId(1L);
        lenient().when(servicoRepository.findById(1L)).thenReturn(Optional.of(servicoMock));

        profissionalService = new ProfissionalService(profissionalRepository, servicoRepository);
    }

    @Test
    public void testGetAllProfissional() {
        Profissional profissional = new Profissional();
        Servico servico = new Servico();
        servico.setId(1L);
        profissional.setServicosHabilitados(new HashSet<>(Arrays.asList(servico)));

        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));

        when(profissionalRepository.save(any(Profissional.class))).thenReturn(profissional);

        Profissional result = profissionalService.createProfissional(profissional);

        assertEquals(profissional, result);

        verify(servicoRepository).findById(1L);
    }

    @Test
    public void testGetProfissionalById() {
        Long id = 1L;
        Profissional profissional = new Profissional();
        profissional.setId(id);

        when(profissionalRepository.findById(id)).thenReturn(Optional.of(profissional));

        Profissional result = profissionalService.getProfissionalById(id);

        assertEquals(profissional.getId(), result.getId(), "O ID do profissional retornado deve ser igual ao ID buscado.");
    }
    @Test
    public void testCreateProfissional() {
        Profissional profissional = new Profissional();
        Servico servico = new Servico();
        servico.setId(1L);
        profissional.setServicosHabilitados(new HashSet<>(Arrays.asList(servico)));

        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));
        when(profissionalRepository.save(profissional)).thenReturn(profissional);

        Profissional result = profissionalService.createProfissional(profissional);

        assertEquals(profissional, result);
    }

    @Test
    public void testDeleteProfissional() {
        Long id = 1L;

        when(profissionalRepository.existsById(id)).thenReturn(true);

        boolean result = profissionalService.deleteProfissional(id);

        assertTrue(result);
    }

    @Test
    public void testDeleteProfissionalNotFound() {
        Long id = 1L;

        when(profissionalRepository.existsById(id)).thenReturn(false);

        boolean result = profissionalService.deleteProfissional(id);

        assertFalse(result);
    }
}
