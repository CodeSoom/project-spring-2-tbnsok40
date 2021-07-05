package project.spring.tbnsok40.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

//    void delete(User, user);

}

