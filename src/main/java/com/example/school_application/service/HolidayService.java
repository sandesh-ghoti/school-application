package com.example.school_application.service;

import com.example.school_application.model.Holiday;
import com.example.school_application.utils.Constants.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class HolidayService {
  private final List<Holiday> holidays = new ArrayList<>();

  public List<Holiday> getHolidays(Type type) {
    if (type == Type.FEDERAL) {
      return holidays.stream()
          .filter(holiday -> holiday.getType() == Type.FEDERAL)
          .collect(Collectors.toList());
    } else {
      return holidays.stream()
          .filter(holiday -> holiday.getType() == Type.FESTIVAL)
          .collect(Collectors.toList());
    }
  }

  public List<Holiday> getHolidays() {
    return holidays;
  }

  public boolean addHoliday(Holiday holiday) {
    holidays.add(holiday);
    return true;
  }
}
