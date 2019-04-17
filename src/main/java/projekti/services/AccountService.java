package projekti.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import projekti.models.Account;
import projekti.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountRepository accountRepository;

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
    
    /**
     * Creates a new Account to database with given values.
     * 
     * @param name
     * @param profileName
     * @param username 
     * @param password
     */
    public void createAccount(String name, String profileName, String username, String password) {
        Account account = new Account();
        account.setName(name);
        account.setProfileName(profileName);
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
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
}