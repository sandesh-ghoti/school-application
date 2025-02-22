package com.example.school_application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
  @GetMapping("/health")
  public ResponseEntity<String> healthcheck() {
    return ResponseEntity.ok().body("healthy");
  }
}
