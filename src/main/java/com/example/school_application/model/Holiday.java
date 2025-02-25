package com.example.school_application.model;

import com.example.school_application.utils.Constants.Type;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Holiday extends BaseEntity {
  @NotBlank(message = "day required")
  private final String day;

  @NotBlank(message = "reason required")
  private final String reason;

  @NotBlank(message = "type required as FESTIVAL or FEDERAL")
  private final Type type;
}
