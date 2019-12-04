package techtonic.academy.twiddler.security;

import io.jsonwebtoken.*;
import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
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

    // Assigns the value of the `JWT_SECRET_KEY` environment variable to the String `JWT_SECRET_KEY`
    // (Note: the environment variable and the Java object do NOT have to have the same name)
    @Value("${JWT_SECRET_KEY}")
    private String JWT_SECRET_KEY;

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
            .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET_KEY).parse(token);
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
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
        String id = (String) claims.get("id");
        return Long.parseLong(id);
    }
}
