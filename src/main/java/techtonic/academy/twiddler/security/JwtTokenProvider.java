package techtonic.academy.twiddler.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import techtonic.academy.twiddler.entity.User;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// The JWT Token Provider is responsible for generating and validating JWT tokens
@Component
public class JwtTokenProvider {

    public String generateToken(Authentication auth) {
        User user = (User) auth.getPrincipal();
        Date now = Date.from(Instant.now());
        Date exp = Date.from(Instant.now().plusSeconds(1800));
        String userId = Long.toString(user.getId());
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("username", user.getUsername());

        return Jwts.builder()
            .setSubject(userId)
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(exp)
            // Here we are simply using the string "secret" as the key to encode and decode JWTs with...
            // DON'T EVER DO THIS!!!
            // The hard-coded string is just used here for demonstrative purposes. For any app running in
            // a production environment, this secret key string should be provided to the application as
            // an environment variable.
            .signWith(SignatureAlgorithm.HS512, "secret")
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            // Using the same string value that was used to encode the token
            // in order to decode it and make sure it's valid
            Jwts.parser().setSigningKey("secret").parse(token);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT");
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT has no claims");
        }
        return false;
    }

    // Utility method for getting the user ID from the token
    public long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        String id = (String) claims.get("id");
        return Long.parseLong(id);
    }
}
