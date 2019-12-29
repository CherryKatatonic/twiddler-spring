package techtonic.academy.twiddler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import techtonic.academy.twiddler.entity.User;
import techtonic.academy.twiddler.repository.UserRepo;
import techtonic.academy.twiddler.security.JwtTokenProvider;
import techtonic.academy.twiddler.security.LoginRequest;
import techtonic.academy.twiddler.service.MapValidationErrorService;
import techtonic.academy.twiddler.service.UserService;
import techtonic.academy.twiddler.validation.UserValidator;

import javax.validation.Valid;

// Provides useful behavior for consuming requests and sending responses in a REST API
@RestController
// The base path for all endpoints in this Controller
@RequestMapping("/api/users")
// Allow requests from this foreign origin (the URL of our React app)
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
public class UserController {

    @Autowired private UserRepo repo;
    @Autowired private UserService userService;
    @Autowired private MapValidationErrorService errorService;
    @Autowired private JwtTokenProvider jwtProvider;
    @Autowired private AuthenticationManager authMan;
    @Autowired private UserValidator validator;

    // GET:/api/users
    @GetMapping("")
    // A Page can be returned to the client containing pagination information
    Page<User> getUsers(
        // Indicates that predefined Pageable Defaults should be used for paging variables
        @PageableDefault
        // Indicates that results should be sorted by creation timestamp beginning with the newest
        @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            // Note that this Pageable is actually a parameter of the `getUsers()` method. The annotations for it
            // are actually included with it inside the parentheses of the method signature:
                // getUsers(@PageableDefault @SortDefault(...) Pageable pageable)
            // It automatically gathers any paging parameters provided in the HTTP request such as page, size, etc...
            Pageable pageable) {
        // The Pageable is passed to the Repo method and a Page is returned from the database
        return repo.findAll(pageable);
    }
    // For more info on Spring Paging, visit https://reflectoring.io/spring-boot-paging/

    // POST:/api/users/signup
    @PostMapping("/signup")
    ResponseEntity<?> signup(@Valid @RequestBody User user, BindingResult result) {
        // Ensure username and password are valid
        validator.validate(user, result);

        ResponseEntity<?> errorMap = errorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    // POST:/api/users/login
    @PostMapping("/login")
    // Here we get the submitted form data (username & password) as a LoginRequest object
    ResponseEntity<?> login(@Valid @RequestBody LoginRequest req, BindingResult result) {
        ResponseEntity<?> errorMap = errorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        // Attempt to authenticate with the details provided and return a Spring Security Authentication object
        Authentication auth = authMan.authenticate(
            new UsernamePasswordAuthenticationToken(
                req.getUsername(),
                req.getPassword()
            )
        );
        // Set the Auth object in the Spring Security Context
        SecurityContextHolder.getContext().setAuthentication(auth);
        // Create a JWT token from the Auth object
        String jwt = "Bearer " + jwtProvider.generateToken(auth);
        // Return the JWT token to the client so they can use it for future requests
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

}
