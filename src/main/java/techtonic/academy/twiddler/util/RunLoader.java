package techtonic.academy.twiddler.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import techtonic.academy.twiddler.service.UserService;

// Implements CommandLineRunner to run when the application is started
@Component
public class RunLoader implements CommandLineRunner {

    private final UserService service;

    @Autowired
    public RunLoader(UserService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
//        String imageUrl = "https://proprofs-cdn.s3.amazonaws.com/images/FC/user_images/misc/2480325861.jpg";
//        this.service.createUser(new User("Kat", "password", imageUrl));
    }
}
