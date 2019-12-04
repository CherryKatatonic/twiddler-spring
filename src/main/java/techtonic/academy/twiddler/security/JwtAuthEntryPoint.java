package techtonic.academy.twiddler.security;

import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Using a custom Auth Entry Point allows us to customize auth error handling
// Here we are simply returning a custom error message to the client if the
// user makes a request that they're not authorized for.
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        res.setContentType("application/json");
        res.setStatus(401);
        res.getWriter().print(new Gson().toJson("Unauthorized"));
    }
}
