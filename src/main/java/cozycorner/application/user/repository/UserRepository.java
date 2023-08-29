package cozycorner.application.user.repository;

import cozycorner.application.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUserEmail(String userEmail);
}