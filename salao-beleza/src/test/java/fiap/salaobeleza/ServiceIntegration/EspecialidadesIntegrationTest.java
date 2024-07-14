package fiap.salaobeleza.ServiceIntegration;

import fiap.salaobeleza.exception.ResourceNotFoundException;
import fiap.salaobeleza.model.Especialidades;
import fiap.salaobeleza.repository.EspecialidadeRepository;
import fiap.salaobeleza.service.EspecialidadeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EspecialidadesIntegrationTest {

    @Mock
    private EspecialidadeRepository servicoRepository;

    @InjectMocks
    private EspecialidadeService servicoService;

    @Test
    public void testGetAllServicos() {
        Especialidades servico1 = new Especialidades(1L, "Corte de Cabelo", 50.0);
        Especialidades servico2 = new Especialidades(2L, "Coloração", 100.0);
        List<Especialidades> expectedServicos = Arrays.asList(servico1, servico2);

        when(servicoRepository.findAll()).thenReturn(expectedServicos);

        List<Especialidades> result = servicoService.getAllEspecialidade();

        assertEquals(expectedServicos, result);
    }

    @Test
    public void testGetServicoById() {
        Especialidades servico = new Especialidades(1L, "Corte de Cabelo", 50.0);
        Optional<Especialidades> optionalServico = Optional.of(servico);

        when(servicoRepository.findById(1L)).thenReturn(optionalServico);

        Especialidades result = servicoService.getEspecialidadeById(1L);

        assertEquals(servico, result);
    }

    @Test
    public void testGetServicoByIdNotFound() {
        Optional<Especialidades> optionalServico = Optional.empty();

        when(servicoRepository.findById(1L)).thenReturn(optionalServico);

        assertThrows(ResourceNotFoundException.class, () -> servicoService.getEspecialidadeById(1L));
    }

    @Test
    public void testCreateServico() {
        Especialidades servico = new Especialidades(1L, "Corte de Cabelo", 50.0);

        when(servicoRepository.save(servico)).thenReturn(servico);

        Especialidades result = servicoService.createEspecialidade(servico);

        assertEquals(servico, result);
    }

    @Test
    public void testUpdateServico() {
        Especialidades servico = new Especialidades(1L, "Corte de Cabelo", 50.0);
        Especialidades updatedServico = new Especialidades(1L, "Coloração", 100.0);

        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));
        when(servicoRepository.save(servico)).thenReturn(updatedServico);

        Especialidades result = servicoService.updateEspecialidade(1L, updatedServico);

        assertEquals(updatedServico, result);
    }

    @Test
    public void testUpdateServicoNotFound() {
        Especialidades updatedServico = new Especialidades(1L, "Coloração", 100.0);

        when(servicoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> servicoService.updateEspecialidade(1L, updatedServico));
    }

    @Test
    public void testDeleteServico() {
        Especialidades servico = new Especialidades(1L, "Corte de Cabelo", 50.0);

        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));

        servicoService.deleteEspecialidade(1L);

        verify(servicoRepository, times(1)).delete(servico);
    }

    @Test
    public void testDeleteServicoNotFound() {
        when(servicoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> servicoService.deleteEspecialidade(1L));
    }
}
