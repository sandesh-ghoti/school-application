package com.example.school_application.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Holiday {
  @NotBlank(message = "day required")
  private final String day;

  @NotBlank(message = "reason required")
  private final String reason;

  @NotBlank(message = "type required as FESTIVAL or FEDERAL")
  private final Type type;

  public static enum Type {
    FESTIVAL,
    FEDERAL
  }
}
