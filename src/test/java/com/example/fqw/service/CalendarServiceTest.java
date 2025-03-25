package com.example.fqw.service;

import com.example.fqw.dto.CalendarDto;
import com.example.fqw.entity.Calendar;
import com.example.fqw.entity.Master;
import com.example.fqw.exception.CalendarNotFoundDateException;
import com.example.fqw.exception.CalendarNotFoundIdException;
import com.example.fqw.mapper.CalendarMapperImpl;
import com.example.fqw.repositories.CalendarRepository;
import com.example.fqw.repositories.MasterRepository;
import com.example.fqw.services.CalendarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalendarServiceTest {
    @Mock
    private CalendarRepository calendarRepository;
    @Mock
    private MasterRepository masterRepository;
    @Mock
    private CalendarMapperImpl calendarMapper;

    @InjectMocks
    private CalendarService calendarService;

    private static final LocalDate NOW_DATE = LocalDate.now();

    @Test
    void getAllCalendarsTestSuccess() {
        var calendar1 = this.getCalendarEntity(1L, null, NOW_DATE);
        var calendar2 = this.getCalendarEntity(2L, null, NOW_DATE.plusDays(2));
        var calendar3 = this.getCalendarEntity(3L, null, NOW_DATE.plusDays(3));

        var calendarDto1 = this.getCalendarDto(1L, NOW_DATE);
        var calendarDto2 = this.getCalendarDto(2L, NOW_DATE.plusDays(2));
        var calendarDto3 = this.getCalendarDto(3L, NOW_DATE.plusDays(3));

        when(calendarRepository.findAll()).thenReturn(List.of(calendar1, calendar2, calendar3));
        when(calendarMapper.toDTO(any(Calendar.class))).thenCallRealMethod();

        assertEquals(List.of(calendarDto1, calendarDto2, calendarDto3), calendarService.getAllCalendars());
    }

    @Test
    void getAllCalendarsTestFailed() {
        when(calendarRepository.findAll()).thenReturn(Collections.emptyList());

        verifyNoInteractions(calendarMapper);

        assertTrue(calendarService.getAllCalendars().isEmpty());
    }

    @Test
    void getCalendarByIdTestSuccess() {
        var id = 1L;

        var calendar = this.getCalendarEntity(1L, null, null);
        var calendarDto = this.getCalendarDto(1L, null);

        when(calendarRepository.findById(id)).thenReturn(Optional.of(calendar));
        when(calendarMapper.toDTO(calendar)).thenCallRealMethod();

        assertEquals(calendarDto, calendarService.getCalendarById(id));
    }

    @Test
    void getCalendarByIdTestFailed() {
        var calendarId = 1L;

        when(calendarRepository.findById(calendarId)).thenReturn(Optional.empty());

        assertThrows(CalendarNotFoundIdException.class, () -> calendarService.getCalendarById(calendarId));

        verifyNoInteractions(calendarMapper);
    }

    @Test
    void getCalendarByDateTestSuccess() {
        var date = LocalDate.of(2025, 2, 13);

        var calendar = this.getCalendarEntity(1L, null, date);
        var calendarDto = this.getCalendarDto(1L, date);

        when(calendarRepository.findCalendarByDate(date)).thenReturn(Optional.of(calendar));
        when(calendarMapper.toDTO(calendar)).thenCallRealMethod();

        assertEquals(calendarDto, calendarService.getCalendarByDate(date));
    }

    @Test
    void getCalendarByDateTestFailed() {
        var date = LocalDate.of(2025, 2, 13);

        when(calendarRepository.findCalendarByDate(date)).thenReturn(Optional.empty());

        assertThrows(CalendarNotFoundDateException.class, () -> calendarService.getCalendarByDate(date));

        verify(calendarMapper, times(0)).toDTO(any(Calendar.class));
        verifyNoInteractions(calendarMapper);
    }

    @Test
    void addCalendarTestSuccess() {
        var calendarId = 1L;
        var masterId = 1L;

        var master = this.getMasterEntity(1L);

        var masterIdList = List.of(masterId);
        var masterList = List.of(master);

        var calendar = this.getCalendarEntity(1L, masterList, null);

        var calendarDto = this.getCalendarDto(1L, null);

        when(calendarRepository.findById(calendarId)).thenReturn(Optional.of(calendar));
        when(masterRepository.findAllByIdIn(masterIdList)).thenReturn(masterList);
        when(calendarRepository.save(any(Calendar.class))).then(invocation -> invocation.getArgument(0));
        when(calendarMapper.toDTO(any(Calendar.class))).thenCallRealMethod();

        assertEquals(calendarDto, calendarService.addCalendar(masterIdList, calendarId));
    }

    @Test
    void addCalendarTestFailed() {
        var calendarId = 1L;
        var masterIdList = List.of(1L);

        when(calendarRepository.findById(calendarId)).thenReturn(Optional.empty());

        assertThrows(CalendarNotFoundIdException.class, () -> calendarService.addCalendar(masterIdList, calendarId));
    }

    @Test
    void addCalendarDayTest() {
        var plusDays = 5;

        var calendarDto = this.getCalendarDto(null, NOW_DATE.plusDays(plusDays));

        when(calendarRepository.save(any(Calendar.class))).then(invocation -> invocation.getArgument(0));
        when(calendarMapper.toDTO(any(Calendar.class))).thenCallRealMethod();

        assertEquals(calendarDto, calendarService.addCalendarDay(plusDays));
    }

    @Test
    void getCalendarsByMasterTestSuccess() {
        var masterId = 1L;

        var master = this.getMasterEntity(masterId);

        var calendar1 = this.getCalendarEntity(1L, List.of(master), null);
        var calendar2 = this.getCalendarEntity(2L, List.of(master), null);

        var calendarDto1 = this.getCalendarDto(1L, null);
        var calendarDto2 = this.getCalendarDto(2L, null);

        var calendarList = List.of(calendar1, calendar2);
        var calendarDtoList = List.of(calendarDto1, calendarDto2);

        when(calendarRepository.findCalendarsByMastersId(masterId)).thenReturn(calendarList);
        when(calendarMapper.toDTO(any(Calendar.class))).thenCallRealMethod();

        assertEquals(calendarDtoList, calendarService.getCalendarsByMaster(masterId));
    }

    @Test
    void getCalendarsByMasterInAndDateGreaterThanEqualTestSuccess() {
        var masterId = 1L;

        var master = new Master();
        master.setId(masterId);

        var calendar1 = this.getCalendarEntity(1L, List.of(master), null);
        var calendar2 = this.getCalendarEntity(2L, List.of(master), null);

        var calendarDto1 = this.getCalendarDto(1L, null);
        var calendarDto2 = this.getCalendarDto(2L, null);

        var calendarList = List.of(calendar1, calendar2);
        var calendarDtoList = List.of(calendarDto1, calendarDto2);

        when(masterRepository.findById(masterId)).thenReturn(Optional.of(master));
        when(calendarRepository.findAllByMastersInAndDateGreaterThanEqual(List.of(master), NOW_DATE)).thenReturn(calendarList);
        when(calendarMapper.toDTO(any(Calendar.class))).thenCallRealMethod();

        assertEquals(calendarDtoList, calendarService.getCalendarsByMasterInAndDateGreaterThanEqual(masterId));
    }


    private Calendar getCalendarEntity(Long calendarId, List<Master> masters, LocalDate date) {
        var calendar = new Calendar();
        calendar.setId(calendarId);
        calendar.setMasters(masters);
        calendar.setDate(date);
        return calendar;
    }


    private CalendarDto getCalendarDto(Long id, LocalDate date) {
        var calendarDto = new CalendarDto();
        calendarDto.setId(id);
        calendarDto.setDate(date);
        return calendarDto;
    }

    private Master getMasterEntity(Long masterId) {
        var master = new Master();
        master.setId(masterId);
        return master;
    }
}