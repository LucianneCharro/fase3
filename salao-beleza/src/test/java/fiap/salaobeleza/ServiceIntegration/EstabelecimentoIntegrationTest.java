package fiap.salaobeleza.ServiceIntegration;

import fiap.salaobeleza.model.Estabelecimento;
import fiap.salaobeleza.repository.EstabelecimentoRepository;
import fiap.salaobeleza.service.EstabelecimentoService;
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
public class EstabelecimentoIntegrationTest {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @MockBean
    private EstabelecimentoRepository estabelecimentoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Estabelecimento estabelecimentoMock = new Estabelecimento();
        estabelecimentoMock.setId(1L);
        estabelecimentoMock.setNome("Estabelecimento Teste");
        estabelecimentoMock.setEndereco("Endereço Teste");
        when(estabelecimentoRepository.findById(1L)).thenReturn(Optional.of(estabelecimentoMock));
    }

    @Test
    public void testBuscarEstabelecimentoPorId() {
        Optional<Estabelecimento> estabelecimentoEncontrado = estabelecimentoService.buscarPorId(1L);

        assertNotNull(estabelecimentoEncontrado);
        assertEquals("Estabelecimento Teste", estabelecimentoEncontrado.get().getNome());
        assertEquals("Endereço Teste", estabelecimentoEncontrado.get().getEndereco());
    }
}