
package com.project.shopapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.project.shopapp.entity.Account;

import java.security.Key;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    @Value("${jwt.expiration}")
    private int expiration;
    @Value("${jwt.secretKey}")
    private String secretKey;

    public String generateToken(Account user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("userId", user.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return (extractEmail(token).equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
}
