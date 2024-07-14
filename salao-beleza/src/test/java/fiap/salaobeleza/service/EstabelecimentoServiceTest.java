package fiap.salaobeleza.service;

import fiap.salaobeleza.model.Estabelecimento;
import fiap.salaobeleza.repository.EstabelecimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EstabelecimentoServiceTest {

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @InjectMocks
    private EstabelecimentoService estabelecimentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvarEstabelecimentoComSucesso() {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setNome("Salão da Lúcia");
        estabelecimento.setEndereco("Rua das Flores, 123");

        when(estabelecimentoRepository.save(any(Estabelecimento.class))).thenReturn(estabelecimento);

        Estabelecimento salvo = estabelecimentoService.salvar(new Estabelecimento());

        assertNotNull(salvo);
        assertEquals("Salão da Lúcia", salvo.getNome());
        assertEquals("Rua das Flores, 123", salvo.getEndereco());
        verify(estabelecimentoRepository, times(1)).save(any(Estabelecimento.class));
    }
}