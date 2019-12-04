package techtonic.academy.twiddler.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import techtonic.academy.twiddler.entity.User;
import techtonic.academy.twiddler.service.CustomUserDetailService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

// The JWT Auth Filter is a Filter that looks at each request from the client and ensures
// that auth is valid before the request is processed by the server.
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired private JwtTokenProvider jwtProvider;
    @Autowired private CustomUserDetailService service;

    // Method that filters each request
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String jwt = getJwtFromRequest(req);

        // If JWT is valid, set auth token on the Security Context
        if (jwt != null && !jwt.isBlank() && jwtProvider.validateToken(jwt)) {
            Long uid = jwtProvider.getUserId(jwt);
            User user = service.loadUserById(uid);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user, null, Collections.emptyList()
            );
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // Pass the request down the chain
        chain.doFilter(req, res);
    }

    // Get the JWT from the Authorization header of the request
    private String getJwtFromRequest(HttpServletRequest req) {
        String bearer = req.getHeader("Authorization");

        if (bearer != null && !bearer.isBlank() && bearer.startsWith("Bearer")) {
            return bearer.substring(7);
        }

        return null;
    }
}
