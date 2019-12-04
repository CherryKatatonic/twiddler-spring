package techtonic.academy.twiddler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import techtonic.academy.twiddler.entity.Twiddle;
import techtonic.academy.twiddler.repository.TwiddleRepo;
import techtonic.academy.twiddler.service.MapValidationErrorService;
import techtonic.academy.twiddler.service.TwiddleService;

import javax.validation.Valid;
import java.security.Principal;

// Provides useful behavior for consuming requests and sending responses in a REST API
@RestController
// The base path for all endpoints in this Controller
@RequestMapping("/api/twiddles")
// Allow requests from this foreign origin (the URL of our React app)
@CrossOrigin("http://localhost:3000")
public class TwiddleController {
    @Autowired TwiddleRepo repo;
    @Autowired TwiddleService service;
    @Autowired private MapValidationErrorService validationService;

    // GET:/api/twiddles
    @GetMapping("")
    // A Page can be returned to the client containing pagination information
    Page<Twiddle> getTwiddles(
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

    // POST:/api/twiddles
    @PostMapping("")
    // Principal refers to the Auth credentials that are present in the Spring Security Context
    ResponseEntity<?> createTwiddle(@Valid @RequestBody Twiddle twiddle, BindingResult result, Principal principal) {

        ResponseEntity<?> errorMap = validationService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        // When a Twiddle is created, it is assigned the User who is currently authenticated
        Twiddle twiddle1 = service.createTwiddle(twiddle, principal.getName());
        return new ResponseEntity<Twiddle>(twiddle1, HttpStatus.CREATED);
    }

}
