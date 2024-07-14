package fiap.salaobeleza.service;

import fiap.salaobeleza.model.Avaliacao;
import fiap.salaobeleza.repository.AvaliacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvaliacaoServiceTest {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarAvaliacao() {
        Avaliacao avaliacao = new Avaliacao();
        when(avaliacaoRepository.save(any(Avaliacao.class))).thenReturn(avaliacao);

        Avaliacao result = avaliacaoService.salvarAvaliacao(avaliacao);

        verify(avaliacaoRepository).save(avaliacao);
        assertEquals(avaliacao, result);
    }
}