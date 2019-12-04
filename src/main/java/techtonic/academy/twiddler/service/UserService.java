package techtonic.academy.twiddler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import techtonic.academy.twiddler.entity.User;
import techtonic.academy.twiddler.repository.UserRepo;

// Business logic between Controller (public API) and Repository (database)
@Service
public class UserService {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepo repo;

    public User createUser(User user) {
        if (userExists(user.getUsername())) {
            System.out.println("Username not available");
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public boolean userExists(String username) {
        User user = repo.findUserByUsername(username);
        System.out.println(user);
        return user != null;
    }
}
