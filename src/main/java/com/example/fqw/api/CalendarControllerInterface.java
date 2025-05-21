package com.example.fqw.api;

import com.example.fqw.dto.CalendarDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/calendar")
public interface CalendarControllerInterface {

    @GetMapping("/getAll")
    List<CalendarDto> getAllCalendar();

    @GetMapping("/getById/{calendarId}")
    CalendarDto getCalendarById(@PathVariable("calendarId") Long calendarId);

    @GetMapping("/getByDate/{date}")
    CalendarDto getCalendarByDate(@PathVariable("date") LocalDate date);

    @GetMapping("/getByMasterInAndDateGreaterThanEqual/{masterId}")
    List<CalendarDto> getCalendarsByMasterInAndDateGreaterThanEqual(@PathVariable("masterId") Long calendarId);

    @DeleteMapping("/deleteById/{calendarId}")
    void deleteCalendarById(@PathVariable("calendarId") Long calendarId);

    @PostMapping("/addNewByDay/{plusDays}")
    CalendarDto addCalendarWithDay(@PathVariable("plusDays") Integer plusDays);

    @PutMapping("/addMasters/{calendarId}")
    CalendarDto addCalendarData(@RequestBody List<Long> mastersId, @PathVariable("calendarId") Long id);

}
