package fiap.salaobeleza.service;

import fiap.salaobeleza.model.Estabelecimento;
import fiap.salaobeleza.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                    estabelecimento.setServicosOferecidos(estabelecimentoAtualizado.getServicosOferecidos());
                    estabelecimento.setProfissionaisDisponiveis(estabelecimentoAtualizado.getProfissionaisDisponiveis());
                    estabelecimento.setHorarioFuncionamento(estabelecimentoAtualizado.getHorarioFuncionamento());
                    estabelecimento.setFotos(estabelecimentoAtualizado.getFotos());
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
}