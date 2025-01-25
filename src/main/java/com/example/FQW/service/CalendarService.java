package com.example.FQW.service;

import com.example.FQW.dto.CalendarDto;
import com.example.FQW.entity.Calendar;
import com.example.FQW.entity.Master;
import com.example.FQW.exception.CalendarException.CalendarNotFoundDateException;
import com.example.FQW.exception.CalendarException.CalendarNotFoundIdException;
import com.example.FQW.mapper.CalendarMapper;
import com.example.FQW.repositories.CalendarRepository;
import com.example.FQW.repositories.MasterRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class CalendarService {

    CalendarRepository calendarRepository;

    MasterRepository masterRepository;

    CalendarMapper calendarMapper;

    public List<CalendarDto> getAll() {
        return calendarRepository.findAll()
                .stream()
                .map(calendarMapper::toDTO)
                .toList();
    }

    public CalendarDto getById(Long id) {
        return calendarRepository.findById(id)
                .map(calendarMapper::toDTO)
                .orElseThrow(CalendarNotFoundIdException::new);
    }

    public CalendarDto getByDate(Date date) {
        return calendarRepository.findCalendarByDate(date)
                .map(calendarMapper::toDTO)
                .orElseThrow(CalendarNotFoundDateException::new);
    }


    public void deleteCalendarById(Long id) {
        calendarRepository.deleteById(id);
    }

    public void deleteCalendarByDate(Date date) {
        calendarRepository.delete(calendarRepository.findCalendarByDate(date)
                .orElseThrow(CalendarNotFoundIdException::new));
    }

    public Calendar getCalendarById(Long id) {
        return calendarRepository.findById(id).orElseThrow(CalendarNotFoundIdException::new);
    }

    /*@Scheduled(cron = "0 1 1 * * ?"cron= "@daily"fixedDelay= 5000)
    private void addDay() {
        var today = new Calendar();
        today.setDate(LocalDate.now());
        calendarRepository.save(today);
        log.info("День создан");
    }*/

    public CalendarDto addCalendar(List<Long> mastersId, Long calendarId) {
        return calendarRepository.findById(calendarId)
                .map(calendar -> {
                    var masters = masterRepository.findAllByIdIn(mastersId);
                    var existsMasters = new ArrayList<>(calendar.getMasters());
                    existsMasters.addAll(masters);
                    var result = new ArrayList<>(new HashSet<>(existsMasters));
                    calendar.setMasters(result);
                    return calendarRepository.save(calendar);
                })
                .map(calendarMapper::toDTO)
                .orElseThrow(CalendarNotFoundIdException::new);
    }

    public CalendarDto addCalendarDay(Integer days) {
        Calendar today = new Calendar();
        today.setDate(LocalDate.now().plusDays(days));
        return calendarMapper.toDTO(calendarRepository.save(today));
    }

    public CalendarDto getCalendarByMaster(Integer days) {
        Calendar today = new Calendar();
        today.setDate(LocalDate.now().plusDays(days));
        return calendarMapper.toDTO(calendarRepository.save(today));
    }

    public List<CalendarDto> getCalendarsByMasterInAndDateGreaterThanEqual(Long masterId, LocalDate date) {
       return masterRepository.findById(masterId)
               .map(master -> calendarRepository.findAllByMastersInAndDateGreaterThanEqual(List.of(master), date))
               .map(calendars -> calendars.stream()
                       .map(calendarMapper::toDTO)
                       .toList())
               .orElseThrow(CalendarNotFoundIdException::new);

    }

}
