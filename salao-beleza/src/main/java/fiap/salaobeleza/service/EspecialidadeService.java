package fiap.salaobeleza.service;

import fiap.salaobeleza.model.Especialidades;
import fiap.salaobeleza.exception.ResourceNotFoundException;
import fiap.salaobeleza.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public List<Especialidades> getAllEspecialidade() {
        return especialidadeRepository.findAll();
    }

    public Especialidades getEspecialidadeById(Long id) {
        Optional<Especialidades> especialidade = especialidadeRepository.findById(id);
        if (especialidade.isPresent()) {
            return especialidade.get();
        } else {
        	throw new ResourceNotFoundException("Servico com id " + id + " não encontrado.");
        }
    }

    public Especialidades createEspecialidade(Especialidades servico) {
        return especialidadeRepository.save(servico);
    }

    public Especialidades updateEspecialidade(Long id, Especialidades especialidadeDetails) {
        Especialidades especialidade = getEspecialidadeById(id);
        especialidade.setDescricao(especialidadeDetails.getDescricao());
        especialidade.setPreco(especialidadeDetails.getPreco());
        return especialidadeRepository.save(especialidade);
    }

    public void deleteEspecialidade(Long id) {
        Especialidades especialidade = getEspecialidadeById(id);
        especialidadeRepository.delete(especialidade);
    }
}
