package com.example.school_application.controller;

import com.example.school_application.model.Holiday;
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
  public ResponseEntity<List<Holiday>> getHolidays(@PathVariable Type filter) {
    return ResponseEntity.ok().body(holidayService.getHolidays(filter));
  }

  @GetMapping("/")
  public ResponseEntity<List<Holiday>> getAllHolidays() {
    return ResponseEntity.ok().body(holidayService.getHolidays());
  }

  @PostMapping("/")
  public ResponseEntity<Boolean> addHolidayEntity(@RequestBody Holiday holiday) {
    return ResponseEntity.ok().body(holidayService.addHoliday(holiday));
  }
}
