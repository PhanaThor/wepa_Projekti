package projekti.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import projekti.models.Account;
import projekti.models.AccountPicture;
import projekti.repositories.AccountPictureRepository;
import projekti.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountPictureRepository accountPictureRepository;

    /**
     * Checks if the Account exists in the database with given username and/or profile name
     * 
     * @param username
     * @param profileName
     * @return {@code true} If the accounts exists in the database, {@code false} otherwise
     */
    public Boolean accountExists(String username, String profileName) {
        if(username.equals("")) {
            return accountRepository.findByProfileName(profileName) != null;
        }
        else if(profileName.equals("")) {
            return accountRepository.findByUsername(username) != null;
        }
        else {
            return accountRepository.findByUsernameAndProfileName(username, profileName) != null;
        }
    }

    public void createAccount(Account account) {
        String rawPassword = account.getPassword();
        account.setPassword(passwordEncoder.encode(rawPassword));        
        accountRepository.save(account);
    }

    /**
     * Returns all {@code Account}s in database 
     * 
     * @return A {@code List} of {@code Account}s.
     */
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Returns the Account for the currently logged user.
     * 
     * @return Current users Account from database.
     */
    public Account getLoggedAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Account currentAccount = accountRepository.findByUsername(username);

        return currentAccount;
    }

    /**
     * Returns the Account with the given profile name.
     * 
     * @param profileName Profile name of the Account to be found.
     * @return Account from the database matching the profile name.
     */
    public Account getAccountByProfileName(String profileName) {
        return accountRepository.findByProfileName(profileName);
    }


	public byte[] getPicture(Long id) {
        Optional<AccountPicture> accountPicture = accountPictureRepository.findById(id);
        if(accountPicture.isPresent()) {
            return accountPicture.get().getContent();
        }

		return null;
	}

    @Transactional
	public void savePicture(String profileName, byte[] bytes, String description) {
        Account viewedAccount = accountRepository.findByProfileName(profileName);
        AccountPicture accountPicture = new AccountPicture();
        accountPicture.setContent(bytes);
        accountPicture.setDescription(description); 
        accountPictureRepository.save(accountPicture);
        viewedAccount.getPictures().add(accountPicture);
	}

    @Transactional
	public void setAsProfilePicture(String profileName, Long id) {
        Account viewedAccount = accountRepository.findByProfileName(profileName);
        AccountPicture accountPicture = accountPictureRepository.findById(id).get();
        viewedAccount.setProfilePicture(accountPicture);
	}
}