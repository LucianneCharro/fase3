package fiap.salaobeleza.service;

import fiap.salaobeleza.model.Cliente;
import fiap.salaobeleza.model.Profissional;
import fiap.salaobeleza.model.PacoteServicos;
import fiap.salaobeleza.model.Servico;
import fiap.salaobeleza.model.Agendamento;
import fiap.salaobeleza.repository.AgendamentoRepository;
import fiap.salaobeleza.repository.ServicoRepository;
import fiap.salaobeleza.repository.PacoteServicosRepository;
import fiap.salaobeleza.repository.ClienteRepository;
import fiap.salaobeleza.repository.ProfissionalRepository;
import fiap.salaobeleza.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ServicoRepository servicoRepository;
    private final PacoteServicosRepository pacoteServicosRepository;
    private final ClienteRepository clienteRepository;
    private final ProfissionalRepository profissionalRepository;

    @Autowired
    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              ServicoRepository servicoRepository,
                              PacoteServicosRepository pacoteServicosRepository,
                              ClienteRepository clienteRepository,
                              ProfissionalRepository profissionalRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.servicoRepository = servicoRepository;
        this.pacoteServicosRepository = pacoteServicosRepository;
        this.clienteRepository = clienteRepository;
        this.profissionalRepository = profissionalRepository;
    }

    public Agendamento createAgendamento(Agendamento agendamento) {
        
        if (agendamento.getCliente() == null || agendamento.getProfissional() == null) {
            throw new IllegalArgumentException("Cliente e Profissional devem ser fornecidos.");
        }

        boolean hasServico = agendamento.getServico() != null;
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
            Servico servico = servicoRepository.findById(agendamento.getServico().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));
            agendamento.setServico(servico);
            agendamento.setPacoteServicos(null); 
        } else {
            PacoteServicos pacoteServicos = pacoteServicosRepository.findById(agendamento.getPacoteServicos().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Pacote de Serviços não encontrado"));
            agendamento.setPacoteServicos(pacoteServicos);
            agendamento.setServico(null); 
        }

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
            agendamentoToUpdate.setServico(agendamentoDetails.getServico());
            agendamentoToUpdate.setPacoteServicos(agendamentoDetails.getPacoteServicos());
            agendamentoToUpdate.setDataHora(agendamentoDetails.getDataHora());
            agendamentoRepository.save(agendamentoToUpdate);
            return ResponseEntity.ok().body(agendamentoToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteAgendamento(Long id) {
        Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
        if (agendamento.isPresent()) {
            agendamentoRepository.delete(agendamento.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

