package fiap.salaobeleza.service;

import fiap.salaobeleza.exception.ResourceNotFoundException;
import fiap.salaobeleza.model.Servico;
import fiap.salaobeleza.repository.ServicoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServicoServiceTest {

    @Mock
    private ServicoRepository servicoRepository;

    @InjectMocks
    private ServicoService servicoService;

    @Test
    public void testGetAllServicos() {
        Servico servico1 = new Servico(1L, "Corte de Cabelo", 50.0);
        Servico servico2 = new Servico(2L, "Coloração", 100.0);
        List<Servico> expectedServicos = Arrays.asList(servico1, servico2);

        when(servicoRepository.findAll()).thenReturn(expectedServicos);

        List<Servico> result = servicoService.getAllServicos();

        assertEquals(expectedServicos, result);
    }

    @Test
    public void testGetServicoById() {
        Servico servico = new Servico(1L, "Corte de Cabelo", 50.0);
        Optional<Servico> optionalServico = Optional.of(servico);

        when(servicoRepository.findById(1L)).thenReturn(optionalServico);

        Servico result = servicoService.getServicoById(1L);

        assertEquals(servico, result);
    }

    @Test
    public void testGetServicoByIdNotFound() {
        Optional<Servico> optionalServico = Optional.empty();

        when(servicoRepository.findById(1L)).thenReturn(optionalServico);

        assertThrows(ResourceNotFoundException.class, () -> servicoService.getServicoById(1L));
    }

    @Test
    public void testCreateServico() {
        Servico servico = new Servico(1L, "Corte de Cabelo", 50.0);

        when(servicoRepository.save(servico)).thenReturn(servico);

        Servico result = servicoService.createServico(servico);

        assertEquals(servico, result);
    }

    @Test
    public void testUpdateServico() {
        Servico servico = new Servico(1L, "Corte de Cabelo", 50.0);
        Servico updatedServico = new Servico(1L, "Coloração", 100.0);

        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));
        when(servicoRepository.save(servico)).thenReturn(updatedServico);

        Servico result = servicoService.updateServico(1L, updatedServico);

        assertEquals(updatedServico, result);
    }

    @Test
    public void testUpdateServicoNotFound() {
        Servico updatedServico = new Servico(1L, "Coloração", 100.0);

        when(servicoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> servicoService.updateServico(1L, updatedServico));
    }

    @Test
    public void testDeleteServico() {
        Servico servico = new Servico(1L, "Corte de Cabelo", 50.0);

        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));

        servicoService.deleteServico(1L);

        verify(servicoRepository, times(1)).delete(servico);
    }

    @Test
    public void testDeleteServicoNotFound() {
        when(servicoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> servicoService.deleteServico(1L));
    }
}
