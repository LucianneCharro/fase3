package fiap.salaobeleza.service;

import fiap.salaobeleza.exception.ResourceNotFoundException;
import fiap.salaobeleza.model.Profissional;
import fiap.salaobeleza.model.Servico;
import fiap.salaobeleza.repository.ProfissionalRepository;
import fiap.salaobeleza.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;

    public ProfissionalService(ProfissionalRepository profissionalRepository, ServicoRepository servicoRepository) {
        this.profissionalRepository = profissionalRepository;
        this.servicoRepository = servicoRepository;
    }

    public List<Profissional> getAllProfissional() {
        return profissionalRepository.findAll();
    }

    public Profissional getProfissionalById(Long id) {
        return profissionalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profissional com id " + id + " não encontrado."));
    }

    public Profissional createProfissional(Profissional profissional) {
        Set<Servico> servicosHabilitados = new HashSet<>();
        for (Servico servico : profissional.getServicosHabilitados()) {
            Servico servicoCompleto = servicoRepository.findById(servico.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Servico com id " + servico.getId() + " não encontrado."));
            servicosHabilitados.add(servicoCompleto);
        }
        profissional.setServicosHabilitados(servicosHabilitados);
        return profissionalRepository.save(profissional);
    }

    public Profissional updateProfissional(Long id, Profissional profissionalDetails) {
        Profissional profissional = getProfissionalById(id);
        profissional.setNome(profissionalDetails.getNome());

        Set<Servico> servicosHabilitados = new HashSet<>();
        for (Servico servico : profissionalDetails.getServicosHabilitados()) {
            Servico servicoCompleto = servicoRepository.findById(servico.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Servico com id " + servico.getId() + " não encontrado."));
            servicosHabilitados.add(servicoCompleto);
        }
        profissional.setServicosHabilitados(servicosHabilitados);
        
        return profissionalRepository.save(profissional);
    }

    public boolean deleteProfissional(Long id) {
        if(profissionalRepository.existsById(id)) {
            profissionalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

