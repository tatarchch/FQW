package com.example.fqw.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@UtilityClass
public class DateTimeUtils {

    public final Set<String> DAILY_SET = Set.of(
            "09:00-10:00",
            "10:00-11:00",
            "11:00-12:00",
            "12:00-13:00",
            "13:00-14:00",
            "14:00-15:00",
            "15:00-16:00",
            "16:00-17:00",
            "17:00-18:00",
            "18:00-19:00",
            "19:00-20:00",
            "20:00-21:00"
    );

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String getNotificationTiming(LocalTime localTime) {
        return String.valueOf(localTime.plusHours(1).getHour())
                .concat(":00-")
                .concat(String.valueOf(localTime.plusHours(2).getHour()))
                .concat(":00");
    }

    public Boolean isTodayAvailableTime(String timing) {
        String[] values = timing.split("-");
        LocalTime time = LocalTime.parse(values[0]);
        return LocalTime.now().plusHours(1).isBefore(time);
    }

    public LocalDate getLocalDateFromString(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public List<String> getFreeTimingList(List<String> timingList, LocalDate date) {
        return DateTimeUtils.DAILY_SET.stream().sorted() //sorted?
                .filter(timing -> !timingList.contains(timing))
                .filter(timing -> LocalDate.now().isBefore(date) || DateTimeUtils.isTodayAvailableTime(timing))
                .toList();
    }

}
