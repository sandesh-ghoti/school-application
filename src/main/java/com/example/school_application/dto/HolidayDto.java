package com.example.school_application.dto;

import com.example.school_application.utils.Constants.Type;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HolidayDto {
  private Long id;

  @NotBlank(message = "day required")
  private String day;

  @NotBlank(message = "reason required")
  private String reason;

  @NotBlank(message = "type required as FESTIVAL or FEDERAL")
  private Type type;
}
