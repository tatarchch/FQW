package com.example.fqw.service;

import com.example.fqw.dto.CalendarDto;
import com.example.fqw.entity.Calendar;
import com.example.fqw.exception.CalendarNotFoundDateException;
import com.example.fqw.exception.CalendarNotFoundIdException;
import com.example.fqw.exception.MasterNotFoundException;
import com.example.fqw.mapper.CalendarMapper;
import com.example.fqw.repositories.CalendarRepository;
import com.example.fqw.repositories.MasterRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CalendarService {

    CalendarRepository calendarRepository;
    MasterRepository masterRepository;
    CalendarMapper calendarMapper;

    public List<CalendarDto> getAllCalendars() {
        return calendarRepository.findAll().stream()
                .map(calendarMapper::toDTO)
                .toList();
    }

    public CalendarDto getCalendarById(Long id) {
        return calendarRepository.findById(id)
                .map(calendarMapper::toDTO)
                .orElseThrow(CalendarNotFoundIdException::new);
    }

    public CalendarDto getCalendarByDate(LocalDate date) {
        return calendarRepository.findCalendarByDate(date)
                .map(calendarMapper::toDTO)
                .orElseThrow(CalendarNotFoundDateException::new);
    }

    public void deleteCalendarById(Long id) {
        calendarRepository.deleteById(id);
    }

    public void deleteCalendarByDate(LocalDate date) {
        calendarRepository.delete(calendarRepository.findCalendarByDate(date)
                .orElseThrow(CalendarNotFoundIdException::new));
    }

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

    public List<CalendarDto> getCalendarsByMaster(Long masterId) {
        return calendarRepository.findCalendarsByMastersId(masterId).stream()
                .map(calendarMapper::toDTO)
                .toList();
    }

    /*public List<CalendarDto> getCalendarsByMasterInAndDateGreaterThanEqual(Long masterId, LocalDate date) {
       return masterRepository.findById(masterId)
               .map(master -> calendarRepository.findAllByMastersInAndDateGreaterThanEqual(List.of(master), date))
               .map(calendars -> calendars.stream()
                       .map(calendarMapper::toDTO)
                       .toList())
               .orElseThrow(MasterNotFoundException::new);

    }*/

    public List<CalendarDto> getCalendarsByMasterInAndDateGreaterThanEqual(Long masterId) {
        return masterRepository.findById(masterId)
                .map(master -> calendarRepository.findAllByMastersInAndDateGreaterThanEqual(List.of(master), LocalDate.now()))
                .map(calendars -> calendars.stream()
                        .map(calendarMapper::toDTO)
                        .toList())
                .orElseThrow(MasterNotFoundException::new);

    }

}
