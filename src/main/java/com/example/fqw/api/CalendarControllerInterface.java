package com.example.fqw.api;

import com.example.fqw.dto.CalendarDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/calendar")
@Validated
public interface CalendarControllerInterface {

    @GetMapping("/getAll")
    List<CalendarDto> getAllCalendar();

    @GetMapping("/getById/{calendarId}")
    CalendarDto getCalendarById(@PathVariable("calendarId")
                                @Positive(message = "Id календаря должен быть положительным")
                                Long calendarId);

    @GetMapping("/getByDate/{date}")
    CalendarDto getCalendarByDate(@PathVariable("date")
                                  @NotNull(message = "Дата обязательна")
                                  @FutureOrPresent(message = "Дата должна быть не раньше, чем сегодня")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                  LocalDate date);

    @GetMapping("/getByMasterInAndDateGreaterThanEqual/{masterId}")
    List<CalendarDto> getCalendarsByMasterInAndDateGreaterThanEqual(@PathVariable("masterId")
                                                                    @Positive(message = "Id мастера должно быть положительным")
                                                                    Long masterId);

    @DeleteMapping("/deleteById/{calendarId}")
    void deleteCalendarById(@PathVariable("calendarId")
                            @Positive(message = "Id календаря должен быть положительным")
                            Long calendarId);

    @PostMapping("/addNewByDay/{plusDays}")
    CalendarDto addCalendarWithDay(@PathVariable("plusDays")
                                   @Positive(message = "Количество дней должно быть положительным")
                                   Integer plusDays);

    @PutMapping("/addMasters/{calendarId}")
    CalendarDto addCalendarData(@RequestBody
                                @NotEmpty(message = "Список мастеров не может быть пустым")
                                List<@Positive(message = "Id мастера должен быть положительным") Long> mastersId,

                                @PathVariable("calendarId")
                                @Positive(message = "Id календаря должен быть положительным")
                                Long calendarId);

}
