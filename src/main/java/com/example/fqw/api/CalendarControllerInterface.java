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
    //@PreAuthorize("hasRole('ADMIN'")
    CalendarDto getCalendarById(@PathVariable("calendarId") Long calendarId);

    @GetMapping("/getByDate/{date}")
    //@PreAuthorize("hasRole('ADMIN')")
    CalendarDto getCalendarByDate(@PathVariable("date") LocalDate date);

    @GetMapping("/getByMasterInAndDateGreaterThanEqual/{calendarId}")
    //@PreAuthorize("hasRole('ADMIN')")
    List<CalendarDto> getCalendarsByMasterInAndDateGreaterThanEqual(@PathVariable("calendarId") Long calendarId);

    @DeleteMapping("/deleteById/{calendarId}")
    //@PreAuthorize("hasRole('ADMIN')")
    void deleteCalendarById(@PathVariable("calendarId") Long calendarId);

    @PostMapping("/addNewByDay/{plusDays}")
    //@PreAuthorize("hasRole('ADMIN')")
    CalendarDto addCalendarWithDay(@PathVariable("plusDays") Integer plusDays);

    @PutMapping("/addMasters/{calendarId}")
    //@PreAuthorize("hasRole('ADMIN')")
    CalendarDto addCalendarData(@RequestBody List<Long> mastersId, @PathVariable("calendarId") Long id);

}
