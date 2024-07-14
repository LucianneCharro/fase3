package fiap.salaobeleza.service;

import fiap.salaobeleza.model.Cliente;
import fiap.salaobeleza.model.Profissional;
import fiap.salaobeleza.model.PacoteServicos;
import fiap.salaobeleza.model.Especialidades;
import fiap.salaobeleza.model.Agendamento;
import fiap.salaobeleza.repository.AgendamentoRepository;
import fiap.salaobeleza.repository.EspecialidadeRepository;
import fiap.salaobeleza.repository.PacoteServicosRepository;
import fiap.salaobeleza.repository.ClienteRepository;
import fiap.salaobeleza.repository.ProfissionalRepository;
import fiap.salaobeleza.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private NotificationService notificationService;

    private final AgendamentoRepository agendamentoRepository;
    private final EspecialidadeRepository especialidadeRepository;
    private final PacoteServicosRepository pacoteServicosRepository;
    private final ClienteRepository clienteRepository;
    private final ProfissionalRepository profissionalRepository;


    @Autowired
    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              EspecialidadeRepository especialidadeRepository,
                              PacoteServicosRepository pacoteServicosRepository,
                              ClienteRepository clienteRepository,
                              ProfissionalRepository profissionalRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.pacoteServicosRepository = pacoteServicosRepository;
        this.clienteRepository = clienteRepository;
        this.profissionalRepository = profissionalRepository;
    }

    public Agendamento createAgendamento(Agendamento agendamento) {
        
        if (agendamento.getCliente() == null || agendamento.getProfissional() == null) {
            throw new IllegalArgumentException("Cliente e Profissional devem ser fornecidos.");
        }

        boolean hasServico = agendamento.getEspecialidade() != null;
        boolean hasPacoteServicos = agendamento.getPacoteServicos() != null;
        if ((hasServico && hasPacoteServicos) || (!hasServico && !hasPacoteServicos)) {
            throw new IllegalArgumentException("Deve ser fornecido apenas um dos seguintes: Serviço ou Pacote de Serviços.");
        }

        Cliente cliente = clienteRepository.findById(agendamento.getCliente().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        agendamento.setCliente(cliente);

        Profissional profissional = profissionalRepository.findById(agendamento.getProfissional().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));
        agendamento.setProfissional(profissional);

        if (hasServico) {
            Especialidades especialidades = especialidadeRepository.findById(agendamento.getEspecialidade().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));
            agendamento.setEspecialidade(especialidades);
            agendamento.setPacoteServicos(null); 
        } else {
            PacoteServicos pacoteServicos = pacoteServicosRepository.findById(agendamento.getPacoteServicos().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Pacote de Serviços não encontrado"));
            agendamento.setPacoteServicos(pacoteServicos);
            agendamento.setEspecialidade(null);
        }
//        notificationService.sendNotification(agendamento.getCliente().getEmail(), "Confirmação de Agendamento", "Seu agendamento foi confirmado para " + agendamento.getDataHora());
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> getAllAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public ResponseEntity<Agendamento> getAgendamentoById(Long id) {
        Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
        if (agendamento.isPresent()) {
            return ResponseEntity.ok().body(agendamento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Agendamento> updateAgendamento(Long id, Agendamento agendamentoDetails) {
        Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
        if (agendamento.isPresent()) {
            Agendamento agendamentoToUpdate = agendamento.get();
            agendamentoToUpdate.setCliente(agendamentoDetails.getCliente());
            agendamentoToUpdate.setProfissional(agendamentoDetails.getProfissional());
            agendamentoToUpdate.setEspecialidade(agendamentoDetails.getEspecialidade());
            agendamentoToUpdate.setPacoteServicos(agendamentoDetails.getPacoteServicos());
            agendamentoToUpdate.setDataHora(agendamentoDetails.getDataHora());
            agendamentoRepository.save(agendamentoToUpdate);
            return ResponseEntity.ok().body(agendamentoToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Agendamento> reagendarAgendamento(Long id, LocalDateTime novaDataHora) {
        Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(id);
        if (agendamentoOptional.isPresent()) {
            Agendamento agendamento = agendamentoOptional.get();
            agendamento.setDataHora(novaDataHora);
            // Notificar cliente e profissional sobre o reagendamento
   //       notificationService.sendNotification(agendamento.getCliente().getEmail(), "Reagendamento de Agendamento", "Seu agendamento foi reagendado para " + novaDataHora.toString());
   //        notificationService.sendNotification(agendamento.getProfissional().getEmail(), "Reagendamento de Agendamento", "Um agendamento foi reagendado para " + novaDataHora.toString());
            // Salvar o agendamento atualizado
            agendamentoRepository.save(agendamento);
            return ResponseEntity.ok(agendamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Void> deleteAgendamento(Long id) {
        Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(id);
        if (agendamentoOptional.isPresent()) {
            Agendamento agendamento = agendamentoOptional.get();
            // Notificar cliente e profissional sobre o cancelamento
   //         notificationService.sendNotification(agendamento.getCliente().getEmail(), "Cancelamento de Agendamento", "Seu agendamento foi cancelado.");
   //         notificationService.sendNotification(agendamento.getProfissional().getEmail(), "Cancelamento de Agendamento", "Um agendamento foi cancelado.");
            // Remover o agendamento
            agendamentoRepository.delete(agendamento);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

