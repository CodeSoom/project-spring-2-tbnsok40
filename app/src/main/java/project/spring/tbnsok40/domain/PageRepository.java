package project.spring.tbnsok40.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PageRepository extends CrudRepository<Page, Long> {

    List<Page> findAll();

    Optional<Page> findById(Long id);

    Page save(Page page);

    void deleteById(Page page);

}
