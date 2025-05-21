package com.example.fqw.services;

import com.example.fqw.dto.CalendarDto;
import com.example.fqw.entity.Calendar;
import com.example.fqw.exception.CalendarNotFoundException;
import com.example.fqw.exception.MasterNotFoundException;
import com.example.fqw.mapper.CalendarMapper;
import com.example.fqw.repositories.CalendarRepository;
import com.example.fqw.repositories.MasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final MasterRepository masterRepository;
    private final CalendarMapper calendarMapper;

    public List<CalendarDto> getAllCalendars() {
        return calendarRepository.findAll().stream()
                .map(calendarMapper::toDTO)
                .toList();
    }

    public CalendarDto getCalendarById(Long id) {
        return calendarRepository.findById(id)
                .map(calendarMapper::toDTO)
                .orElseThrow(() -> new CalendarNotFoundException(id));
    }

    public CalendarDto getCalendarByDate(LocalDate date) {
        return calendarRepository.findCalendarByDate(date)
                .map(calendarMapper::toDTO)
                .orElseThrow(() -> new CalendarNotFoundException(date));
    }

    public void deleteCalendarById(Long id) {
        calendarRepository.deleteById(id);
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
                .orElseThrow(() -> new CalendarNotFoundException(calendarId));
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

    public List<CalendarDto> getCalendarsByMasterInAndDateGreaterThanEqual(Long masterId) {
        return masterRepository.findById(masterId)
                .map(master -> calendarRepository.findAllByMastersInAndDateGreaterThanEqual(List.of(master), LocalDate.now()))
                .map(calendars -> calendars.stream()
                        .map(calendarMapper::toDTO)
                        .toList())
                .orElseThrow(() -> new MasterNotFoundException(masterId));

    }

}
