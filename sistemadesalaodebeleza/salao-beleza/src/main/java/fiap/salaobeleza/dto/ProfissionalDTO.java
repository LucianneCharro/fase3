package fiap.salaobeleza.dto;

import fiap.salaobeleza.model.Servico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProfissionalDTO {

    private Long id;

    @NotEmpty
    private String nome;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String telefone;

    @NotEmpty
    private Set<Servico> servicosHabilitados;

    public ProfissionalDTO(Long id, String nome, String email, String telefone, Set<Servico> servicosHabilitados) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.servicosHabilitados = servicosHabilitados;
    }
}
