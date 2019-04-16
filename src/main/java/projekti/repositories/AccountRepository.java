package projekti.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import projekti.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @EntityGraph(value = "Account.picturePictures")
    List<Account> findAll();
    @EntityGraph(value = "Account.picturePictures")
    Account findByUsername(String username);
    @EntityGraph(value = "Account.picturePictures")
    Account findByProfileName(String profileName);
}