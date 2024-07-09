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
        // Outras configurações de mock necessárias

        Servico servicoMock = new Servico();
        servicoMock.setId(1L);
        // Configurar outros campos necessários do servicoMock
        // Usar lenient() para o stubbing que pode não ser necessário em todos os testes
        lenient().when(servicoRepository.findById(1L)).thenReturn(Optional.of(servicoMock));

        // Inicialização do ProfissionalService com os mocks configurados
        profissionalService = new ProfissionalService(profissionalRepository, servicoRepository);
    }

    @Test
    public void testGetAllProfissional() {
        Profissional profissional = new Profissional();
        Servico servico = new Servico();
        servico.setId(1L);
        profissional.setServicosHabilitados(new HashSet<>(Arrays.asList(servico)));

        // Configuração do mock para garantir que o findById retorna um Optional de Servico
        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servico));

        // Mock do comportamento de save para retornar o profissional
        when(profissionalRepository.save(any(Profissional.class))).thenReturn(profissional);

        Profissional result = profissionalService.createProfissional(profissional);

        // Verificar se o profissional retornado é igual ao esperado
        assertEquals(profissional, result);

        // Verificação adicional para garantir que o findById está sendo chamado corretamente
        verify(servicoRepository).findById(1L);
    }

    @Test
    public void testGetProfissionalById() {
        // ID do profissional a ser buscado
        Long id = 1L;
        // Criação de um profissional mock para simular o retorno do repositório
        Profissional profissional = new Profissional();
        profissional.setId(id);

        // Configuração do mock para retornar o profissional quando o método findById for chamado com o ID correto
        when(profissionalRepository.findById(id)).thenReturn(Optional.of(profissional));

        // Chamada do método a ser testado
        Profissional result = profissionalService.getProfissionalById(id);

        // Verificar se o profissional retornado é igual ao esperado
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
