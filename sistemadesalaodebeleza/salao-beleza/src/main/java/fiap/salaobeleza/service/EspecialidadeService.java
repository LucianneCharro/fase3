package fiap.salaobeleza.service;

import fiap.salaobeleza.model.Especialidades;
import fiap.salaobeleza.exception.ResourceNotFoundException;
import fiap.salaobeleza.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Especialidades> getAllServicos() {
        return servicoRepository.findAll();
    }

    public Especialidades getServicoById(Long id) {
        Optional<Especialidades> servico = servicoRepository.findById(id);
        if (servico.isPresent()) {
            return servico.get();
        } else {
        	throw new ResourceNotFoundException("Servico com id " + id + " não encontrado.");
        }
    }

    public Especialidades createServico(Especialidades servico) {
        return servicoRepository.save(servico);
    }

    public Especialidades updateServico(Long id, Especialidades servicoDetails) {
        Especialidades servico = getServicoById(id);
        servico.setDescricao(servicoDetails.getDescricao());
        servico.setPreco(servicoDetails.getPreco());
        return servicoRepository.save(servico);
    }

    public void deleteServico(Long id) {
        Especialidades servico = getServicoById(id);
        servicoRepository.delete(servico);
    }
}
