package com.example.school_application.service;

import com.example.school_application.dto.HolidayDto;
import com.example.school_application.mapper.HolidayMapper;
import com.example.school_application.model.Holiday;
import com.example.school_application.repository.HolidayRepository;
import com.example.school_application.utils.Constants.Type;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class HolidayService {
  private final HolidayRepository holidayRepository;

  public HolidayService(HolidayRepository holidayRepository) {
    this.holidayRepository = holidayRepository;
  }

  public List<HolidayDto> getHolidays(Type type) {
    List<Holiday> holidays = holidayRepository.findByType(type);

    return holidays.stream().map(HolidayMapper::toHolidayDto).collect(Collectors.toList());
  }

  public List<HolidayDto> getHolidays() {
    List<Holiday> holidays = holidayRepository.findAll();
    return holidays.stream().map(HolidayMapper::toHolidayDto).toList();
  }

  public boolean addHoliday(HolidayDto holidayDto) {
    Holiday holiday = holidayRepository.save(HolidayMapper.toHoliday(holidayDto));
    return holiday == null ? false : true;
  }
}
