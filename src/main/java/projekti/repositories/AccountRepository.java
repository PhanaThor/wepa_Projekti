package projekti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projekti.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    Account findByProfileName(String profileName);
}