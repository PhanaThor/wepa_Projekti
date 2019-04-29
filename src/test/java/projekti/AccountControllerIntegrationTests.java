package projekti;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.fluentlenium.adapter.junit.FluentTest;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import projekti.models.Account;
import projekti.repositories.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AccountControllerIntegrationTests extends FluentTest {
    @LocalServerPort
    private Integer port;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void init() {
        if (accountRepository.findByUsername("Thomas") == null) {
            Account account = new Account();
            account.setUsername("Thomas");
            account.setPassword(passwordEncoder.encode("O'Malley"));
            account.setProfileName("alleycat");
            account.setName("Thomas O'Malley");
            accountRepository.save(account);
        }

        getDriver().manage().deleteAllCookies();
    }

    @Test
    public void userCanSeeOwnProfileLinkAfterLogin() throws Throwable {
        goTo("http://localhost:" + port + "/users");
        assertThat(pageSource()).doesNotContain("alleycat");
        enterDetailsAndSubmit("Thomas", "O'Malley");
        assertThat(pageSource()).contains("alleycat");
    }

    private void enterDetailsAndSubmit(String username, String password) {
        $(By.name("username")).fill().with(username);
        $(By.name("password")).fill().with(password);
        $(By.name("password")).submit();
    }
}