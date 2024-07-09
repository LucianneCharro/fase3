package fiap.salaobeleza.service;

import fiap.salaobeleza.model.Servico;
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

    public List<Servico> getAllServicos() {
        return servicoRepository.findAll();
    }

    public Servico getServicoById(Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        if (servico.isPresent()) {
            return servico.get();
        } else {
        	throw new ResourceNotFoundException("Servico com id " + id + " não encontrado.");
        }
    }

    public Servico createServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Servico updateServico(Long id, Servico servicoDetails) {
        Servico servico = getServicoById(id);
        servico.setDescricao(servicoDetails.getDescricao());
        servico.setPreco(servicoDetails.getPreco());
        return servicoRepository.save(servico);
    }

    public void deleteServico(Long id) {
        Servico servico = getServicoById(id);
        servicoRepository.delete(servico);
    }
}
