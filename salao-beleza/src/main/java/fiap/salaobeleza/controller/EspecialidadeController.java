package fiap.salaobeleza.controller;

import fiap.salaobeleza.model.Especialidades;
import fiap.salaobeleza.service.EspecialidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @GetMapping
    public List<Especialidades> getAllEspecialidade() {
        return especialidadeService.getAllEspecialidade();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidades> getEspecialidadeById(@PathVariable Long id) {
        Especialidades servico = especialidadeService.getEspecialidadeById(id);
        return new ResponseEntity<>(servico, HttpStatus.OK);
    }

    @PostMapping
    public Especialidades createEspecialidade(@RequestBody Especialidades servico) {
        return especialidadeService.createEspecialidade(servico);
    }

    @PutMapping("/{id}")
    public Especialidades updateEspecialidade(@PathVariable Long id, @RequestBody Especialidades especialidadeDetails) {
        return especialidadeService.updateEspecialidade(id, especialidadeDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecialidade(@PathVariable Long id) {
        especialidadeService.deleteEspecialidade(id);
        return ResponseEntity.noContent().build();
    }
}
