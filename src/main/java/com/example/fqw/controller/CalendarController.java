package com.example.fqw.controller;


import com.example.fqw.dto.CalendarDto;
import com.example.fqw.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/calendars")
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/calendars")
    public List<CalendarDto> getAllCalendar() {
        return calendarService.getAllCalendars();
    }

    @GetMapping("/calendarById/{calendarId}")
    public CalendarDto getCalendarById(@PathVariable("calendarId") Long calendarId) {
        return calendarService.getCalendarById(calendarId);
    }

    @GetMapping("/calendarByDate/{date}")
    public CalendarDto getCalendarByDate(@PathVariable("date") LocalDate date) {
        return calendarService.getCalendarByDate(date);
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
