package com.topico.service;

import com.topico.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${spring.security.jwt.secret}")
    private String SECRET;

    @Value("${spring.security.jwt.expireHours}")
    private Integer EXPIRE_HOURS;

    public String getUserEmail(String jwtToken) {
        return getClaim(jwtToken, Claims::getSubject);
    }

    public String generateToken(Map<String, Object> extraClaims, User user) {
        long now = System.currentTimeMillis();
        return Jwts.builder().setClaims(extraClaims).setSubject(user.getEmail()).setIssuedAt(new Date(now)).setExpiration(new Date(now + EXPIRE_HOURS * 60 * 60 * 1000)).signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    public String generateToken(User user) {
        return generateToken(new HashMap<>(), user);
    }

    public boolean isTokenValid(String jwtToken, User user) {
        final String email = getUserEmail(jwtToken);
        return email.equals(user.getEmail()) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        return getExpireTime(jwtToken).before(new Date());
    }

    private Date getExpireTime(String jwtToken) {
        return getClaim(jwtToken, Claims::getExpiration);
    }

    public <T> T getClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaims(String jwtToken) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(jwtToken).getBody();
    }

    private Key getSigningKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(bytes);
    }
}
