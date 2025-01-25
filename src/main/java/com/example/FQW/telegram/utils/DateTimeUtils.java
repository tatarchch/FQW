package com.example.FQW.telegram.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalTime;
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

    public Boolean isTodayAvailableTime(String daily) {
        String[] pair = daily.split("-");
        LocalTime time = LocalTime.parse(pair[1]).minusHours(1);
        return LocalTime.now().isBefore(time);
    }

}
