package fiap.salaobeleza.ServiceIntegration;

import fiap.salaobeleza.model.Cliente;
import fiap.salaobeleza.repository.ClienteRepository;
import fiap.salaobeleza.service.ClienteService;
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
public class ClienteIntegrationTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Cliente clienteMock = new Cliente();
        clienteMock.setId(1L);
        clienteMock.setNome("Cliente Teste");
        // Configura o mock para retornar o clienteMock quando findById(1L) for chamado
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteMock));
    }

    @Test
    public void testBuscarClientePorId() {
        // Ação: busca o cliente por ID
        Cliente clienteEncontrado = clienteService.getClienteById(1L);

        // Verificação: o cliente foi encontrado corretamente
        assertNotNull(clienteEncontrado);
        assertEquals("Cliente Teste", clienteEncontrado.getNome());
    }
}