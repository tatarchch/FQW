package com.example.FQW.mapper;

import com.example.FQW.dto.CalendarDto;
import com.example.FQW.entity.Calendar;
import org.mapstruct.Mapper;

@Mapper
public interface CalendarMapper {

    Calendar toEntity(CalendarDto calendarDto);

    CalendarDto toDTO(Calendar calendar);

}
