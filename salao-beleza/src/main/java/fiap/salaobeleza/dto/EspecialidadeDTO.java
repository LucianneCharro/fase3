package fiap.salaobeleza.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class EspecialidadeDTO {

    private Long id;

    @NotEmpty
    private String descricao;

    @Positive
    private double preco;

    public EspecialidadeDTO(Long id, String descricao, double preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }
}
