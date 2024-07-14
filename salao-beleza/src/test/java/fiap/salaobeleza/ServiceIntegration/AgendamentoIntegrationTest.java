package fiap.salaobeleza.ServiceIntegration;

import fiap.salaobeleza.model.Agendamento;
import fiap.salaobeleza.repository.AgendamentoRepository;
import fiap.salaobeleza.service.AgendamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AgendamentoIntegrationTest {

    @Autowired
    private AgendamentoService agendamentoService;

    @MockBean
    private AgendamentoRepository agendamentoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Agendamento agendamentoMock = new Agendamento();
        agendamentoMock.setId(1L);

        when(agendamentoRepository.findById(1L)).thenReturn(Optional.of(agendamentoMock));
    }

    @Test
    public void testBuscarAgendamentoPorId() {
        ResponseEntity<Agendamento> resposta = agendamentoService.getAgendamentoById(1L);

        assertEquals(HttpStatus.OK, resposta.getStatusCode(), "Status da resposta não é OK");

        assertNotNull(resposta.getBody(), "Corpo da resposta é nulo");

        assertEquals(1L, resposta.getBody().getId(), "ID do agendamento não corresponde");
    }
}