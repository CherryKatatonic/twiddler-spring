package techtonic.academy.twiddler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techtonic.academy.twiddler.entity.User;
import techtonic.academy.twiddler.repository.UserRepo;

// The Custom User Details Service overrides methods to customize functionality from Spring's UserDetailsService
// The UserDetails class provides our User class with a wrapper that enables Spring Security to work with User objects
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user == null) System.out.println("User not found");
        return user;
    }

    // Indicates that this method is Transactional
    // For more info on the Spring @Transactional annotation, visit this SO thread:
    // https://stackoverflow.com/questions/1099025/spring-transactional-what-happens-in-background
    @Transactional
    public User loadUserById(Long id){
        User user = userRepo.findUserById(id);
        if (user == null) System.out.println("User not found");
        return user;

    }
}
