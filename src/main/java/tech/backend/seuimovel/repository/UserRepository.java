package tech.backend.seuimovel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.backend.seuimovel.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
}
