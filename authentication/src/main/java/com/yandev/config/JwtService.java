package com.yandev.config;

import com.yandev.user.User;
import com.yandev.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final UserService userService;

    public JwtService(UserService userService) {
        this.userService = userService;
    }

    public Map<String, String> generateJwt(String username){
        User user =  this.userService.loadUserByUsername(username);
        return  this.generateJwt(user);
    }

    public Map<String, String> generateJwt(User user){
        final long currentTime = System.currentTimeMillis();
        final long currentTimeExpiration = currentTime + 30 * 60 * 1000;
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("password", user.getPassword());
        claims.put(Claims.EXPIRATION, currentTimeExpiration);
        claims.put(Claims.SUBJECT, user.getUsername());
        claims.put(Claims.ISSUED_AT, currentTime);
        String BEARER = "Bearer";
        return Map.of(BEARER, Jwts
                .builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTimeExpiration))
                .setSubject(user.getUsername())
                .setClaims(claims)
                .signWith(getKey())
                .compact());
    }

    private Key getKey(){
        String ENCRYPTION_KEY = "dd5e9770f0a84810f98cdf97b96c99fc9c928643b3a46394ce0a02ef6dd7df9f";
        final byte[] decoders = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoders);
    }

    public String extractUsername(String token){
        return this.getClaim(token,Claims::getSubject);
    }

    public boolean isTokenExpired(String token){
        Date expirationDate = this.getClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    private <T> T getClaim(String token, Function<Claims,T> function){
        Claims claims = getClaims(token);
        return function.apply(claims);
    }

    private Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
