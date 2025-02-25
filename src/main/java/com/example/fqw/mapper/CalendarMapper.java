package com.example.fqw.mapper;

import com.example.fqw.dto.CalendarDto;
import com.example.fqw.entity.Calendar;
import org.mapstruct.Mapper;

@Mapper
public interface CalendarMapper {

    Calendar toEntity(CalendarDto calendarDto);

    CalendarDto toDTO(Calendar calendar);

}
