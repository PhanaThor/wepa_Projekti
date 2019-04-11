package projekti.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekti.models.User;
import projekti.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Boolean doLogin(String username, String password) {
        User currentUser = userRepository.findByLoginName(username);
        
        return false; //currentUser.getPassword().equals(password);
    }
}