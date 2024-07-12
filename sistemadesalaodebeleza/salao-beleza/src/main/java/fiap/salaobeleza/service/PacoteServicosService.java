package fiap.salaobeleza.service;

import fiap.salaobeleza.model.PacoteServicos;
import fiap.salaobeleza.model.Especialidades;
import fiap.salaobeleza.repository.PacoteServicosRepository;
import fiap.salaobeleza.repository.EspecialidadeRepository;
import fiap.salaobeleza.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Service
public class PacoteServicosService {

    private final PacoteServicosRepository pacoteServicosRepository;
    private final EspecialidadeRepository servicoRepository;

    public PacoteServicosService(PacoteServicosRepository pacoteServicosRepository, EspecialidadeRepository servicoRepository) {
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
        Set<Especialidades> servicos = new HashSet<>();
        for (Especialidades servico : pacoteServicos.getEspecialidades()) {
            Especialidades servicoCompleto = servicoRepository.findById(servico.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Servico", "id", servico.getId()));
            servicos.add(servicoCompleto);
        }
        pacoteServicos.setEspecialidades(servicos);
        return pacoteServicosRepository.save(pacoteServicos);
    }

    public PacoteServicos updatePacoteServicos(Long id, PacoteServicos pacoteServicosDetails) {
        PacoteServicos pacoteServicos = getPacoteServicosById(id);

        Set<Especialidades> servicos = new HashSet<>();
        for (Especialidades servico : pacoteServicosDetails.getEspecialidades()) {
            Especialidades servicoCompleto = servicoRepository.findById(servico.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Servico", "id", servico.getId()));
            servicos.add(servicoCompleto);
        }
        pacoteServicos.setEspecialidades(servicos);
        pacoteServicos.setNome(pacoteServicosDetails.getNome());

        return pacoteServicosRepository.save(pacoteServicos);
    }

    public void deletePacoteServicos(Long id) {
        PacoteServicos pacoteServicos = getPacoteServicosById(id);
        pacoteServicosRepository.delete(pacoteServicos);
    }
}
