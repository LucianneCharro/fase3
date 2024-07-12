package fiap.salaobeleza.controller;

import fiap.salaobeleza.model.Especialidades;
import fiap.salaobeleza.service.ServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public List<Especialidades> getAllServicos() {
        return servicoService.getAllServicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidades> getServicoById(@PathVariable Long id) {
        Especialidades servico = servicoService.getServicoById(id);
        return new ResponseEntity<>(servico, HttpStatus.OK);
    }

    @PostMapping
    public Especialidades createServico(@RequestBody Especialidades servico) {
        return servicoService.createServico(servico);
    }

    @PutMapping("/{id}")
    public Especialidades updateServico(@PathVariable Long id, @RequestBody Especialidades servicoDetails) {
        return servicoService.updateServico(id, servicoDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable Long id) {
        servicoService.deleteServico(id);
        return ResponseEntity.noContent().build();
    }
}
