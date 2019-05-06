package projekti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projekti.models.Account;
import projekti.models.AccountPicture;

public interface AccountPictureRepository extends JpaRepository<AccountPicture, Long> {
    AccountPicture findByOwnerAndId(Account owner, Long id);
}