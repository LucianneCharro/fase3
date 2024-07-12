package fiap.salaobeleza.service;

import fiap.salaobeleza.model.Disponibilidade;
import fiap.salaobeleza.repository.DisponibilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DisponibilidadeService {

    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;

    public boolean verificarDisponibilidade(Long profissionalId, LocalDateTime inicio, LocalDateTime fim) {
        List<Disponibilidade> disponibilidades = disponibilidadeRepository.findByProfissionalIdAndInicioLessThanEqualAndFimGreaterThanEqual(profissionalId, inicio, fim);
        return disponibilidades.isEmpty();
    }

    public Disponibilidade adicionarDisponibilidade(Long profissionalId, LocalDateTime inicio, LocalDateTime fim) {
        Disponibilidade novaDisponibilidade = new Disponibilidade();
        novaDisponibilidade.setProfissionalId(profissionalId);
        novaDisponibilidade.setInicio(inicio);
        novaDisponibilidade.setFim(fim);
        return disponibilidadeRepository.save(novaDisponibilidade);
    }

    public void removerDisponibilidade(Long id) {
        disponibilidadeRepository.deleteById(id);
    }

}