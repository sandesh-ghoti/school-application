package com.example.school_application.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

// @Component
@RequiredArgsConstructor
public class JWTUtils {
  @Value("${jwt.secret.key}")
  private String secretKey;
  @Value("${jwt.expirationMs}")
  private Long expirationMs;
  private SecretKey key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
  }

  public String generateToken(UserDetails user) {
    return Jwts.builder()
        .subject(user.getUsername())
        .claim("roles", user.getAuthorities().stream().map(authority -> authority.getAuthority()).toList())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expirationMs))
        .signWith(key)
        .compact();
  }

  public String getUsernameFromToken(String token) {
    return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return username != null && !username.isEmpty() && !isTokenExpired(token)
        && userDetails.getUsername().equals(username);
  }

  public boolean isTokenExpired(String token) {
    return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getExpiration()
        .before(new Date(System.currentTimeMillis()));
  }
}
