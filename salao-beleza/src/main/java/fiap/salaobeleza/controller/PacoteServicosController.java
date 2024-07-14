package fiap.salaobeleza.controller;

import fiap.salaobeleza.model.PacoteServicos;
import fiap.salaobeleza.service.PacoteServicosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;
import java.util.List;

@RestController
@RequestMapping("/pacoteservicos")
public class PacoteServicosController {

    private final PacoteServicosService pacoteServicosService;

    public PacoteServicosController(PacoteServicosService pacoteServicosService) {
        this.pacoteServicosService = pacoteServicosService;
    }

    @GetMapping
    public List<PacoteServicos> getAllPacoteServicos() {
        return pacoteServicosService.getAllPacoteServicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacoteServicos> getPacoteServicosById(@PathVariable Long id) {
        return ResponseEntity.ok(pacoteServicosService.getPacoteServicosById(id));
    }

    @PostMapping
    public ResponseEntity<?> createPacoteServicos(@Validated @RequestBody PacoteServicos pacoteServicos, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        PacoteServicos createdPacoteServicos = pacoteServicosService.createPacoteServicos(pacoteServicos);
        return new ResponseEntity<>(createdPacoteServicos, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public PacoteServicos updatePacoteServicos(@PathVariable Long id, @Validated @RequestBody PacoteServicos pacoteServicosDetails) {
        return pacoteServicosService.updatePacoteServicos(id, pacoteServicosDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacoteServicos(@PathVariable Long id) {
        pacoteServicosService.deletePacoteServicos(id);
        return ResponseEntity.noContent().build();
    }
}
