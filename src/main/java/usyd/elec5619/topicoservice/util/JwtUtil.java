package usyd.elec5619.topicoservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.expiration-ms}")
    private Long JWT_EXPIRATION;

    public String generateToken(String username) {
        final Date currentTime = new Date(System.currentTimeMillis());
        final Date expireTime = new Date(System.currentTimeMillis() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentTime)
                .setExpiration(expireTime)  // 7 days
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }
    
}
