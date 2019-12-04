package techtonic.academy.twiddler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techtonic.academy.twiddler.entity.Twiddle;
import techtonic.academy.twiddler.entity.User;
import techtonic.academy.twiddler.repository.TwiddleRepo;
import techtonic.academy.twiddler.repository.UserRepo;

// Business logic between Controller (public API) and Repository (database)
@Service
public class TwiddleService {
    @Autowired TwiddleRepo twiddleRepo;
    @Autowired UserRepo userRepo;

    public Twiddle createTwiddle(Twiddle twiddle, String username) {
        User user = userRepo.findUserByUsername(username);
        twiddle.setUser(user);
        return twiddleRepo.save(twiddle);
    }
}
