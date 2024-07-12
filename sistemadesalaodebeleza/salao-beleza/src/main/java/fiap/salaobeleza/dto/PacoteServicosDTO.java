package fiap.salaobeleza.dto;

import fiap.salaobeleza.model.Especialidades;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PacoteServicosDTO {

    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private Set<Especialidades> especialidades;

    public PacoteServicosDTO(Long id, String nome, Set<Especialidades> especialidades) {
        this.id = id;
        this.nome = nome;
        this.especialidades = especialidades;
    }
}
