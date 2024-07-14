package fiap.salaobeleza.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Especialidades> especialidadeOferecidos;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Profissional> profissionaisDisponiveis;

    @Column(nullable = false)
    private String horarioFuncionamento;

    @ElementCollection
    private List<String> fotos;

}