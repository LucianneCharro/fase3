package fiap.salaobeleza.repository;

import fiap.salaobeleza.model.Especialidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidades, Long> {

}