package fiap.salaobeleza.repository;

import fiap.salaobeleza.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long>, JpaSpecificationExecutor<Estabelecimento> {

}