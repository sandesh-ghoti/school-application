package com.example.school_application.controller;

import com.example.school_application.dto.UserDto;
import com.example.school_application.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "users")
@RequiredArgsConstructor
@RestController
public class UsersController {
  private final UserService userService;

  @GetMapping("")
  public ResponseEntity<List<UserDto>> getAllUser() {
    var users = userService.getAllUser();
    return ResponseEntity.ok().body(users);
  }

  @PostMapping("")
  public ResponseEntity<UserDto> createuser(@RequestBody(required = true) @Valid UserDto userDto) {
    var user = userService.saveUser(userDto);
    return ResponseEntity.ok().body(user);
  }
}
