package com.example.school_application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.school_application.dto.AuthRequest;
import com.example.school_application.dto.AuthResponse;
import com.example.school_application.dto.UserDto;
import com.example.school_application.service.UserService;
import com.example.school_application.utils.RSAJWTUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping(path = "auth")
@RequiredArgsConstructor
@RestController
public class UsersController {
  private final UserService userService;
  // private final JWTUtils jwtUtils;
  private final RSAJWTUtils jwtUtils;
  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;

  @GetMapping("users")
  public ResponseEntity<List<UserDto>> getAllUser() {
    var users = userService.getAllUser();
    return ResponseEntity.ok().body(users);
  }

  @PostMapping("register")
  public ResponseEntity<?> createuser(@RequestBody @Valid UserDto userDto) {
    var user = userService.saveUser(userDto);
    var userDetails = userDetailsService.loadUserByUsername(user.getEmail());
    String token = jwtUtils.generateToken(userDetails);
    AuthResponse authResponse = new AuthResponse();
    authResponse.setToken(token);
    return ResponseEntity.ok().body(authResponse);
  }

  @PostMapping("login")
  public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest) {
    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
    var userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
    String token = jwtUtils.generateToken(userDetails);
    AuthResponse authResponse = new AuthResponse();
    authResponse.setToken(token);
    return ResponseEntity.ok().body(authResponse);
  }

}
