package fiap.salaobeleza.service;

import fiap.salaobeleza.model.PacoteServicos;
import fiap.salaobeleza.model.Servico;
import fiap.salaobeleza.repository.PacoteServicosRepository;
import fiap.salaobeleza.repository.ServicoRepository;
import fiap.salaobeleza.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Service
public class PacoteServicosService {

    private final PacoteServicosRepository pacoteServicosRepository;
    private final ServicoRepository servicoRepository;

    public PacoteServicosService(PacoteServicosRepository pacoteServicosRepository, ServicoRepository servicoRepository) {
        this.pacoteServicosRepository = pacoteServicosRepository;
        this.servicoRepository = servicoRepository;
    }

    public List<PacoteServicos> getAllPacoteServicos() {
        return pacoteServicosRepository.findAll();
    }

    public PacoteServicos getPacoteServicosById(Long id) {
        return pacoteServicosRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("PacoteServicos", "id", id));
    }

    public PacoteServicos createPacoteServicos(PacoteServicos pacoteServicos) {
        Set<Servico> servicos = new HashSet<>();
        for (Servico servico : pacoteServicos.getServicos()) {
            Servico servicoCompleto = servicoRepository.findById(servico.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Servico", "id", servico.getId()));
            servicos.add(servicoCompleto);
        }
        pacoteServicos.setServicos(servicos);
        return pacoteServicosRepository.save(pacoteServicos);
    }

    public PacoteServicos updatePacoteServicos(Long id, PacoteServicos pacoteServicosDetails) {
        PacoteServicos pacoteServicos = getPacoteServicosById(id);

        Set<Servico> servicos = new HashSet<>();
        for (Servico servico : pacoteServicosDetails.getServicos()) {
            Servico servicoCompleto = servicoRepository.findById(servico.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Servico", "id", servico.getId()));
            servicos.add(servicoCompleto);
        }
        pacoteServicos.setServicos(servicos);
        pacoteServicos.setNome(pacoteServicosDetails.getNome());

        return pacoteServicosRepository.save(pacoteServicos);
    }

    public void deletePacoteServicos(Long id) {
        PacoteServicos pacoteServicos = getPacoteServicosById(id);
        pacoteServicosRepository.delete(pacoteServicos);
    }
}
