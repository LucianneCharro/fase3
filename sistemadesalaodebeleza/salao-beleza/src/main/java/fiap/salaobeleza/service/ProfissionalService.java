package fiap.salaobeleza.service;

import fiap.salaobeleza.exception.ResourceNotFoundException;
import fiap.salaobeleza.model.Profissional;
import fiap.salaobeleza.model.Especialidades;
import fiap.salaobeleza.repository.ProfissionalRepository;
import fiap.salaobeleza.repository.EspecialidadeRepository;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;
    private final EspecialidadeRepository especialidadeRepository;

    public ProfissionalService(ProfissionalRepository profissionalRepository, EspecialidadeRepository especialidadeRepository) {
        this.profissionalRepository = profissionalRepository;
        this.especialidadeRepository = especialidadeRepository;
    }

    public List<Profissional> getAllProfissional() {
        return profissionalRepository.findAll();
    }

    public Profissional getProfissionalById(Long id) {
        return profissionalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profissional com id " + id + " não encontrado."));
    }

    public Profissional createProfissional(Profissional profissional) {
        Set<Especialidades> especialidades = new HashSet<>();
        for (Especialidades especialidade: profissional.getEspecialidades()) {
            Especialidades especialidadeCompleto = especialidadeRepository.findById(especialidade.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade com id " + especialidade.getId() + " não encontrado."));
            especialidades.add(especialidadeCompleto);
        }
        profissional.setEspecialidades(especialidades);
        return profissionalRepository.save(profissional);
    }

    public Profissional updateProfissional(Long id, Profissional profissionalDetails) {
        Profissional profissional = getProfissionalById(id);
        profissional.setNome(profissionalDetails.getNome());

        Set<Especialidades> especialidades = new HashSet<>();
        for (Especialidades servico : profissionalDetails.getEspecialidades()) {
            Especialidades servicoCompleto = especialidadeRepository.findById(servico.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Servico com id " + servico.getId() + " não encontrado."));
            especialidades.add(servicoCompleto);
        }
        profissional.setEspecialidades(especialidades);
        
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

