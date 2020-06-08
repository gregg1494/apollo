package com.greggvandycke.Apollo.security.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.greggvandycke.Apollo.models.User;
import com.greggvandycke.Apollo.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil implements Serializable {

    private final Clock clock = DefaultClock.INSTANCE;

    @Value("${jwt.secret}")
    private String secret;

    private Map<String,String> userTokensMap = new HashMap();

    private UserRepository userRepository;

    public String getUsernameFromToken(String token) {
        // if(!getUserTokensMap().containsValue(token)) {
        //	 throw new ExpiredJwtException(null, null, "Token has been revoked!");
        //}
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        // Add token to database
        Optional<User> user = userRepository.findByUsername(subject);
        user.ifPresent(value -> value.setToken(token));
        userRepository.save(user.get());

        getUserTokensMap().put(subject, token);

        return token;
    }

    public void invalidateToken(String username) {
        getUserTokensMap().remove(username);
        //Remove token from DB
        Optional<User> user = userRepository.findByUsername(username);
        user.get().setToken("");
        userRepository.save(user.get());
    }

    public Map<String, String> getUserTokensMap() {
        //query will be called once when jwtUtil created//
        if(userTokensMap.isEmpty()) {
            userRepository.findAll().forEach( (user) -> {
                userTokensMap.put(user.getUsername(),user.getToken());
            } );
        }
        return userTokensMap;
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        return (
                username.equals(user.getUsername())
        );
    }
}
