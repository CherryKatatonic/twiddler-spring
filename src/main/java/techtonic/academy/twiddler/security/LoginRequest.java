package techtonic.academy.twiddler.security;

import javax.validation.constraints.NotBlank;

// The Login Request is used as a parameter in the Controller method "login"
// If we try to consume the login request parameters as a User object like we
// do with "signup", our JwtAuthEntryPoint throws an error.
public class LoginRequest {

    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
