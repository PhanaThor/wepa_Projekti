package projekti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projekti.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByProfilename(String profilename);
    Account findByUsername(String username);
}