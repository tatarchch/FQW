package com.example.fqw.controller;


import com.example.fqw.api.CalendarControllerInterface;
import com.example.fqw.dto.CalendarDto;
import com.example.fqw.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalendarControllerImpl implements CalendarControllerInterface {

    private final CalendarService calendarService;

    @Override
    public List<CalendarDto> getAllCalendar() {
        return calendarService.getAllCalendars();
    }

    @Override
    public CalendarDto getCalendarById(Long calendarId) {
        return calendarService.getCalendarById(calendarId);
    }

    @Override
    public CalendarDto getCalendarByDate(LocalDate date) {
        return calendarService.getCalendarByDate(date);
    }

    @Override
    public List<CalendarDto> getCalendarsByMasterInAndDateGreaterThanEqual(Long calendarId) {
        return calendarService.getCalendarsByMasterInAndDateGreaterThanEqual(calendarId);
    }

    @Override
    public void deleteCalendarById(Long calendarId) {
        calendarService.deleteCalendarById(calendarId);
    }

    @Override
    public CalendarDto addCalendarWithDay(Integer plusDays) {
        return calendarService.addCalendarDay(plusDays);
    }

    @Override
    public CalendarDto addCalendarData(List<Long> mastersId, Long id) {
        return calendarService.addCalendar(mastersId, id);
    }

}
