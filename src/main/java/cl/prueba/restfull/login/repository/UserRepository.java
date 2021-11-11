package cl.prueba.restfull.login.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.prueba.restfull.login.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByName(String name);
  List<User> findByEmail(String email);
 
}
