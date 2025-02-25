package com.example.school_application.model;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity {
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
}
