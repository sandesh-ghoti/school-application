package com.example.school_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class SchoolApplication {

  public static void main(String[] args) {
    SpringApplication.run(SchoolApplication.class, args);
  }
}
