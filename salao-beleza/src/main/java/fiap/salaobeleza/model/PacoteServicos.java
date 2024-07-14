package fiap.salaobeleza.model;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import jakarta.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PacoteServicos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotEmpty
    private Set<Especialidades> especialidades;
    
    @Transient
    private Double preco;
    
    public Double getPreco() {
        if (this.especialidades != null && !this.especialidades.isEmpty()) {
            double totalPreco = especialidades.stream()
                .mapToDouble(Especialidades::getPreco)
                .sum();
            this.preco = totalPreco * 0.9;
        }
        return this.preco;
    }

	public Set<Especialidades> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<Especialidades> especialidades) {
		this.especialidades = especialidades;
	}
    
    
}
