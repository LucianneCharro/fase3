package fiap.salaobeleza.repository;

import fiap.salaobeleza.model.Disponibilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {
    List<Disponibilidade> findByProfissionalIdAndInicioLessThanEqualAndFimGreaterThanEqual(Long profissionalId, LocalDateTime inicio, LocalDateTime fim);
}