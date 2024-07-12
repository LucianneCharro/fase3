package fiap.salaobeleza.controller;

import fiap.salaobeleza.model.Agendamento;
import fiap.salaobeleza.model.Disponibilidade;
import fiap.salaobeleza.service.AgendamentoService;
import fiap.salaobeleza.service.DisponibilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @Autowired
    private DisponibilidadeService disponibilidadeService;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    /**
     * Calcula a soma de dois números.
     * @param agendamento O primeiro número.
     * @return A soma dos dois números.
     **/


    @PostMapping
    public ResponseEntity<Agendamento> createAgendamento(@RequestBody Agendamento agendamento) {
        // Verifica a disponibilidade do profissional
        boolean disponivel = disponibilidadeService.verificarDisponibilidade(agendamento.getProfissional().getId(), agendamento.getDataHora(), agendamento.getDataHora().plusHours(1)); // Considerando agendamentos de 1 hora
        if (!disponivel) {
            throw new IllegalStateException("Profissional não está disponível no horário solicitado.");
        }
        Disponibilidade createdDisponibilidade = disponibilidadeService.adicionarDisponibilidade(agendamento.getProfissional().getId(), agendamento.getDataHora(), agendamento.getDataHora().plusHours(1));
        Agendamento createdAgendamento = agendamentoService.createAgendamento(agendamento);
        return ResponseEntity.ok(createdAgendamento);
    }

    @GetMapping
    public List<Agendamento> getAllAgendamentos() {
        return agendamentoService.getAllAgendamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getAgendamentoById(@PathVariable Long id) {
        return agendamentoService.getAgendamentoById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> updateAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamentoDetails) {
        return agendamentoService.updateAgendamento(id, agendamentoDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarAgendamento(@PathVariable Long id) {
        disponibilidadeService.removerDisponibilidade(id);
        return agendamentoService.deleteAgendamento(id);
    }

    public ResponseEntity<Agendamento> reagendarAgendamento(Long id, LocalDateTime novaDataHora) {
        return agendamentoService.reagendarAgendamento(id, novaDataHora);

    }
}
