package com.example.school_application.model;

import com.example.school_application.utils.Constants.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "holidays")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Holiday extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "holiday_day", unique = true, nullable = false)
  private String day;

  @Column(nullable = false)
  private String reason;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Type type;
}
