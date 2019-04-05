package projekti.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projekti.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProfileName(String profileName);
}