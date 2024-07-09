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
    private Set<Servico> servicos;
    
    @Transient
    private Double preco;
    
    public Double getPreco() {
        if (this.servicos != null && !this.servicos.isEmpty()) {
            double totalPreco = servicos.stream()
                .mapToDouble(Servico::getPreco)
                .sum();
            this.preco = totalPreco * 0.9;
        }
        return this.preco;
    }

	public Set<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(Set<Servico> servicos) {
		this.servicos = servicos;
	}
    
    
}
