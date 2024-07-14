package fiap.salaobeleza.ServiceIntegration;

import fiap.salaobeleza.model.Avaliacao;
import fiap.salaobeleza.repository.AvaliacaoRepository;
import fiap.salaobeleza.service.AvaliacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AvaliacaoIntegrationTest {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @MockBean
    private AvaliacaoRepository avaliacaoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Avaliacao avaliacaoMock = new Avaliacao();
        avaliacaoMock.setId(1L);
        when(avaliacaoRepository.findById(1L)).thenReturn(Optional.of(avaliacaoMock));
    }

    @Test
    public void testSalvarAvaliacao() {
        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setId(1L);
        when(avaliacaoRepository.save(any(Avaliacao.class))).thenReturn(novaAvaliacao);

        Avaliacao resultado = avaliacaoService.salvarAvaliacao(novaAvaliacao);

        assertEquals(1L, resultado.getId(), "ID da avaliação não corresponde");
    }
}