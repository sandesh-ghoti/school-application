package com.example.school_application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

  @NotBlank(message = "Name must not be blank")
  @Size(min = 3, message = "Name must be at least 3 characters long")
  private String name;

  @NotBlank(message = "Email must not be blank")
  @Email(message = "Please provide a valid email address")
  private String email;

  @NotBlank(message = "Password must not be blank")
  @Size(min = 6, message = "Password must be at least 6 characters long")
  private String password;
}
