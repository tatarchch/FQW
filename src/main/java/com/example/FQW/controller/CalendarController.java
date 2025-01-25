package com.example.FQW.controller;


import com.example.FQW.dto.CalendarDto;
import com.example.FQW.service.CalendarService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/calendars")
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/calendars")
    public List<CalendarDto> getAllCalendar() {
        return calendarService.getAll();
    }

    @GetMapping("/calendarById/{calendarId}")
    public CalendarDto getCalendarById(@PathVariable("calendarId") Long calendarId) {
        return calendarService.getById(calendarId);
    }

    @GetMapping("/getCalendarsByMasterInAndDateGreaterThanEqual/{calendarId}")
    public List<CalendarDto> getCalendarsByMasterInAndDateGreaterThanEqual(@PathVariable("calendarId") Long calendarId, @PathVariable LocalDate localDate) {
        return calendarService.getCalendarsByMasterInAndDateGreaterThanEqual(calendarId, localDate);
    }

    @PostMapping("/calendarDeleteById/{calendarId}")
    public void deleteCalendarById(@PathVariable("calendarId") Long calendarId) {
        calendarService.deleteCalendarById(calendarId);
    }

    @PostMapping("/add/{calendarId}")
    public CalendarDto addCalendar(@RequestBody List<Long> mastersId, @PathVariable("calendarId") Long calendarId) {
        return calendarService.addCalendar(mastersId, calendarId);
    }

    @PostMapping("/addNewDay/{plusDays}")
    public CalendarDto addCalendar(@PathVariable("plusDays") Integer plusDays) {
        return calendarService.addCalendarDay(plusDays);
    }

}
