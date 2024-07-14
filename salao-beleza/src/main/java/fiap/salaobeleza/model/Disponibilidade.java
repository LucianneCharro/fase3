package fiap.salaobeleza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Disponibilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long profissionalId;
    private LocalDateTime inicio;
    private LocalDateTime fim;
}