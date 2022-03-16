package by.paul.monsterservice.repository;

import by.paul.monsterservice.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  boolean existsUserByEmail(String email);
  Optional<User> findByEmail(String email);
}
