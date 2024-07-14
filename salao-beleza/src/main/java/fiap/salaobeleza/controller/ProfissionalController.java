package fiap.salaobeleza.controller;

import fiap.salaobeleza.model.Profissional;
import fiap.salaobeleza.service.ProfissionalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    public List<Profissional> getAllProfissional() {
        return profissionalService.getAllProfissional();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> getProfissionalById(@PathVariable(value = "id") Long profissionalId) {
        Profissional profissional = profissionalService.getProfissionalById(profissionalId);
        return ResponseEntity.ok().body(profissional);
    }

    @PostMapping
    public Profissional createProfissional(@Valid @RequestBody Profissional profissional) {
        return profissionalService.createProfissional(profissional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> updateProfissional(@PathVariable(value = "id") Long profissionalId,
                                                          @Valid @RequestBody Profissional profissionalDetails) {
        Profissional updatedProfissional = profissionalService.updateProfissional(profissionalId, profissionalDetails);
        return ResponseEntity.ok(updatedProfissional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfissional(@PathVariable(value = "id") Long profissionalId) {
        boolean isDeleted = profissionalService.deleteProfissional(profissionalId);
        if(isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
