package com.example.school_application.mapper;

import com.example.school_application.dto.HolidayDto;
import com.example.school_application.model.Holiday;

public class HolidayMapper {
  public static Holiday toHoliday(HolidayDto holidayDto) {
    Holiday holiday = new Holiday();
    holiday.setReason(holidayDto.getReason());
    holiday.setDay(holidayDto.getDay());
    holiday.setType(holidayDto.getType());
    return holiday;
  }

  public static HolidayDto toHolidayDto(Holiday holiday) {
    HolidayDto holidayDto = new HolidayDto();
    holidayDto.setId(holiday.getId());
    holidayDto.setReason(holiday.getReason());
    holidayDto.setDay(holiday.getDay());
    holidayDto.setType(holiday.getType());
    return holidayDto;
  }
}
