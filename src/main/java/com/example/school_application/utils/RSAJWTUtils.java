package com.example.school_application.utils;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RSAJWTUtils {

  @Value("${jwt.expirationMs}")
  private Long expirationMs;

  private final JwtEncoder jwtEncoder;
  private final JwtDecoder jwtDecoder;

  public String generateToken(UserDetails userDetails) {
    Instant instant = Instant.now();
    JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder().subject(userDetails.getUsername())
        .claim("scope", userDetails.getAuthorities().stream().map(authority -> authority.getAuthority()).toList())
        .issuedAt(instant).expiresAt(instant.plusMillis(expirationMs)).build();
    return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
  }

  public String getUsernameFromToken(String token) {
    return jwtDecoder.decode(token).getSubject();
  }

  public boolean isTokenExpired(String token) {
    Instant instant = Instant.now();
    Instant expiresAt = jwtDecoder.decode(token).getExpiresAt();
    return expiresAt != null && expiresAt.isBefore(instant);
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return username != null && !username.isEmpty() && !isTokenExpired(token)
        && userDetails.getUsername().equals(username);
  }
}
