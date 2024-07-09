package fiap.salaobeleza.service;

import fiap.salaobeleza.model.Agendamento;
import fiap.salaobeleza.model.Cliente;
import fiap.salaobeleza.model.Profissional;
import fiap.salaobeleza.model.Servico;
import fiap.salaobeleza.repository.AgendamentoRepository;
import fiap.salaobeleza.repository.ClienteRepository;
import fiap.salaobeleza.repository.ProfissionalRepository;
import fiap.salaobeleza.repository.ServicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AgendamentoServiceTest {

    private AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository agendamentoRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ProfissionalRepository profissionalRepository;
    @Mock
    private ServicoRepository servicoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        agendamentoService = new AgendamentoService(agendamentoRepository, servicoRepository, null, clienteRepository, profissionalRepository);
        Cliente clienteMock = new Cliente();
        clienteMock.setId(1L);
        clienteMock.setNome("Maria");
        clienteMock.setEmail("L@gmail.com");
        // Mock para retornar o cliente quando o findById for chamado
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteMock));

        Profissional profissionalMock = new Profissional();
        profissionalMock.setId(1L);
        profissionalMock.setNome("Maria");
        // Mock para retornar o profissional quando o findById for chamado
        when(profissionalRepository.findById(1L)).thenReturn(Optional.of(profissionalMock));

        Servico servicoMock = new Servico();
        servicoMock.setId(1L);
        // Mock para retornar o servico quando o findById for chamado
        when(servicoRepository.findById(1L)).thenReturn(Optional.of(servicoMock));
    }

    @Test
    public void testCreateAgendamento() {

        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Profissional profissional = new Profissional();
        profissional.setId(1L);

        Servico servico = new Servico();
        servico.setId(1L);

        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);
        agendamento.setCliente(cliente);
        agendamento.setProfissional(profissional);
        agendamento.setServico(servico);

        when(agendamentoRepository.save(any(Agendamento.class))).thenReturn(agendamento);

        Agendamento createdAgendamento = agendamentoService.createAgendamento(agendamento);

        assertEquals(agendamento.getId(), createdAgendamento.getId());
    }

    @Test
    public void testGetAgendamentoById() {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);

        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamento));

        ResponseEntity<Agendamento> response = agendamentoService.getAgendamentoById(1L);

        assertEquals(agendamento.getId(), response.getBody().getId());
    }

    @Test
    public void testUpdateAgendamento() {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);

        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamento));
        when(agendamentoRepository.save(any(Agendamento.class))).thenReturn(agendamento);

        Agendamento updatedAgendamento = new Agendamento();
        updatedAgendamento.setId(1L);

        ResponseEntity<Agendamento> response = agendamentoService.updateAgendamento(1L, updatedAgendamento);

        assertEquals(agendamento.getId(), response.getBody().getId());
    }

    @Test
    public void testDeleteAgendamento() {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(1L);

        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamento));

        ResponseEntity<Void> response = agendamentoService.deleteAgendamento(1L);

        assertEquals(200, response.getStatusCodeValue());
    }
}

