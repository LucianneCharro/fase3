package fiap.salaobeleza.service;

import fiap.salaobeleza.model.Estabelecimento;
import fiap.salaobeleza.repository.EstabelecimentoRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public List<Estabelecimento> listarTodos() {
        return estabelecimentoRepository.findAll();
    }

    public Optional<Estabelecimento> buscarPorId(Long id) {
        return estabelecimentoRepository.findById(id);
    }

    public Estabelecimento salvar(Estabelecimento estabelecimento) {
        return estabelecimentoRepository.save(estabelecimento);
    }

    public Optional<Estabelecimento> atualizar(Long id, Estabelecimento estabelecimentoAtualizado) {
        return estabelecimentoRepository.findById(id)
                .map(estabelecimento -> {
                    estabelecimento.setNome(estabelecimentoAtualizado.getNome());
                    estabelecimento.setEndereco(estabelecimentoAtualizado.getEndereco());
                    estabelecimento.setHorarioFuncionamento(estabelecimentoAtualizado.getHorarioFuncionamento());
                    return estabelecimentoRepository.save(estabelecimento);
                });
    }

    public boolean deletar(Long id) {
        return estabelecimentoRepository.findById(id)
                .map(estabelecimento -> {
                    estabelecimentoRepository.delete(estabelecimento);
                    return true;
                }).orElse(false);
    }
    public List<Estabelecimento> buscarEstabelecimentos(String nome, String endereco, String horarioFuncionamento) {
        return estabelecimentoRepository.findAll((Specification<Estabelecimento>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (nome != null) {
                predicates.add(criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}