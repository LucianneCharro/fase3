package fiap.salaobeleza.controller;

import fiap.salaobeleza.model.Estabelecimento;
import fiap.salaobeleza.model.PacoteServicos;
import fiap.salaobeleza.model.Profissional;
import fiap.salaobeleza.model.Servico;
import fiap.salaobeleza.repository.ProfissionalRepository;
import fiap.salaobeleza.repository.ServicoRepository;
import fiap.salaobeleza.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ProfissionalRepository profissionalRepository;
    @GetMapping
    public List<Estabelecimento> listarTodos() {
        return estabelecimentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estabelecimento> buscarPorId(@PathVariable Long id) {
        return estabelecimentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping
//    public Estabelecimento adicionar(@RequestBody Estabelecimento estabelecimento) {
//        return estabelecimentoService.salvar(estabelecimento);
//    }

    @PostMapping
    public Estabelecimento adicionar(@RequestBody Map<String, Object> payload) {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setNome((String) payload.get("nome"));
        estabelecimento.setEndereco((String) payload.get("endereco"));
        estabelecimento.setHorarioFuncionamento((String) payload.get("horarioFuncionamento"));
        estabelecimento.setFotos((List<String>) payload.get("fotos"));

        // Carregar e associar serviços oferecidos
        List<Integer> servicosOferecidosIds = (List<Integer>) payload.get("servicosOferecidosIds");
        Set<Servico> servicosOferecidos = servicosOferecidosIds.stream()
                .map(id -> servicoRepository.findById(id.longValue()).orElse(null))
                .collect(Collectors.toSet());
        estabelecimento.setServicosOferecidos(servicosOferecidos);

        // Carregar e associar profissionais disponíveis
        List<Integer> profissionaisDisponiveisIds = (List<Integer>) payload.get("profissionaisDisponiveisIds");
        Set<Profissional> profissionaisDisponiveis = profissionaisDisponiveisIds.stream()
                .map(id -> profissionalRepository.findById(id.longValue()).orElse(null))
                .collect(Collectors.toSet());
        estabelecimento.setProfissionaisDisponiveis(profissionaisDisponiveis);

        return estabelecimentoService.salvar(estabelecimento);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Estabelecimento> atualizar(@PathVariable Long id, @RequestBody Estabelecimento estabelecimento) {
        return estabelecimentoService.atualizar(id, estabelecimento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (estabelecimentoService.deletar(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}