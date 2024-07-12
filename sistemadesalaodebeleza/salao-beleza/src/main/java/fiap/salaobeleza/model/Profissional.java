package fiap.salaobeleza.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import jakarta.persistence.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String nome;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String telefone;

    @ManyToMany
    @NotEmpty
    private Set<Especialidades> especialidades;

    private String horariosDisponiveis;

    public Set<Especialidades> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<Especialidades> especialidades) {
		this.especialidades = especialidades;
	}
}

