package project.spring.tbnsok40.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {

    List<Portfolio> findAll();

    Optional<Portfolio> findById(Long id);

    Portfolio save(Portfolio portfolio);

    void deleteById(Portfolio portfolio);

}
