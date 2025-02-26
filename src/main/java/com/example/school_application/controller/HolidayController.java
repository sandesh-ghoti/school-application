package com.example.school_application.controller;

import com.example.school_application.dto.HolidayDto;
import com.example.school_application.service.HolidayService;
import com.example.school_application.utils.Constants.Type;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/holidays")
@RestController
public class HolidayController {
  private final HolidayService holidayService;

  public HolidayController(HolidayService holidayService) {
    this.holidayService = holidayService;
  }

  @GetMapping("/{filter}")
  public ResponseEntity<List<HolidayDto>> getHolidays(@PathVariable String filter) {
    Type type = Type.valueOf(filter.toUpperCase());
    return ResponseEntity.ok().body(holidayService.getHolidays(type));
  }

  @GetMapping("/")
  public ResponseEntity<List<HolidayDto>> getAllHolidays() {
    return ResponseEntity.ok().body(holidayService.getHolidays());
  }

  @PostMapping("/")
  public ResponseEntity<Boolean> addHolidayEntity(@RequestBody HolidayDto holidayDto) {
    return ResponseEntity.ok().body(holidayService.addHoliday(holidayDto));
  }
}
