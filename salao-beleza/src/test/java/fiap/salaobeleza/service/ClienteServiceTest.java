package fiap.salaobeleza.service;

import fiap.salaobeleza.model.Cliente;
import fiap.salaobeleza.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    public void testCreateCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setEmail("joao@example.com");
        cliente.setTelefone("123456789");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente savedCliente = clienteService.createCliente(cliente);

        assertThat(savedCliente).isNotNull();
        assertThat(savedCliente.getNome()).isEqualTo(cliente.getNome());
        assertThat(savedCliente.getEmail()).isEqualTo(cliente.getEmail());
        assertThat(savedCliente.getTelefone()).isEqualTo(cliente.getTelefone());
    }

    @Test
    public void testGetAllClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Maria");
        cliente1.setEmail("maria@example.com");
        cliente1.setTelefone("987654321");

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Pedro");
        cliente2.setEmail("pedro@example.com");
        cliente2.setTelefone("456789123");

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> clientes = clienteService.getAllClientes();

        assertThat(clientes).hasSize(2);
        assertThat(clientes).contains(cliente1, cliente2);
    }

    @Test
    public void testGetClienteById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Ana");
        cliente.setEmail("ana@example.com");
        cliente.setTelefone("333333333");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente foundCliente = clienteService.getClienteById(1L);

        assertThat(foundCliente).isNotNull();
        assertThat(foundCliente.getId()).isEqualTo(1L);
        assertThat(foundCliente.getNome()).isEqualTo(cliente.getNome());
        assertThat(foundCliente.getEmail()).isEqualTo(cliente.getEmail());
        assertThat(foundCliente.getTelefone()).isEqualTo(cliente.getTelefone());
    }

    @Test
    public void testUpdateCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("José");
        cliente.setEmail("jose@example.com");
        cliente.setTelefone("111111111");

        Cliente updatedCliente = new Cliente();
        updatedCliente.setId(1L);
        updatedCliente.setNome("José da Silva");
        updatedCliente.setEmail("jose.silva@example.com");
        updatedCliente.setTelefone("222222222");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(updatedCliente);

        Cliente result = clienteService.updateCliente(1L, updatedCliente);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNome()).isEqualTo(updatedCliente.getNome());
        assertThat(result.getEmail()).isEqualTo(updatedCliente.getEmail());
        assertThat(result.getTelefone()).isEqualTo(updatedCliente.getTelefone());
    }

    @Test
    public void testDeleteCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Fernanda");
        cliente.setEmail("fernanda@example.com");
        cliente.setTelefone("777777777");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        clienteService.deleteCliente(1L);

        verify(clienteRepository, times(1)).delete(cliente);
    }
}
