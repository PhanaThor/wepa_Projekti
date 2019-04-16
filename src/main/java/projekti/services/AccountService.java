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


    public Boolean userExists(String username, String profileName) {
        if(username.equals("")) {
            return accountRepository.findByProfileName(profileName) != null;
        }
        else if(profileName.equals("")) {
            return accountRepository.findByUsername(username) != null;
        }

        return false;
    }

    public void createAccount(String username, String password, String name, String profileName) {
        Account account = new Account();
        account.setName(name);
        account.setProfileName(profileName);
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getLoggedAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Account currentAccount = accountRepository.findByUsername(username);

        return currentAccount;
    }

    public Account getAccountByProfileName(String profileName) {
        return accountRepository.findByProfileName(profileName);
    }
}