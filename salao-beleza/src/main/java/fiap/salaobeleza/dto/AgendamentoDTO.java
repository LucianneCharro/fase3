package fiap.salaobeleza.dto;

import fiap.salaobeleza.model.Cliente;
import fiap.salaobeleza.model.Profissional;
import fiap.salaobeleza.model.PacoteServicos;
import fiap.salaobeleza.model.Especialidades;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDTO {

    private Long id;
    private Cliente cliente;
    private Profissional profissional;
    private Especialidades especialidade;
    private PacoteServicos pacoteServicos;
    private LocalDateTime dataHora;
}

