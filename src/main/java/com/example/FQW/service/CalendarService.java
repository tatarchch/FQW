package com.example.FQW.service;

import com.example.FQW.dto.CalendarDto;
import com.example.FQW.entity.Calendar;
import com.example.FQW.mapper.CalendarMapper;
import com.example.FQW.repositories.CalendarRepository;
import com.example.FQW.response.exception.CalendarException.CalendarNotFoundDateException;
import com.example.FQW.response.exception.CalendarException.CalendarNotFoundIdException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    private final CalendarMapper calendarMapper;

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

}
