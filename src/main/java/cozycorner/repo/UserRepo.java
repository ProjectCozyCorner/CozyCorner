package cozycorner.repo;

import cozycorner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User save(User user);
    List<User> findAll();
}
