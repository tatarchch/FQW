package com.example.fqw.service;

import com.example.fqw.dto.RecordDto;
import com.example.fqw.entity.Master;
import com.example.fqw.entity.Record;
import com.example.fqw.exception.MasterNotFoundException;
import com.example.fqw.exception.OtherException;
import com.example.fqw.exception.RecordException;
import com.example.fqw.mapper.RecordMapperImpl;
import com.example.fqw.repositories.MasterRepository;
import com.example.fqw.repositories.RecordRepository;
import com.example.fqw.services.RecordService;
import com.example.fqw.utils.DateTimeUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecordServiceTest {
    @Mock
    private RecordRepository recordRepository;
    @Mock
    private RecordMapperImpl recordMapper;
    @Mock
    private MasterRepository masterRepository;

    @InjectMocks
    private RecordService recordService;

    @Test
    void getAllTest() {
        var record1 = this.getRecordEntity(1L, null, null);
        var record2 = this.getRecordEntity(2L, null, null);

        var recordDto1 = this.getRecordDto(1L, null);
        var recordDto2 = this.getRecordDto(2L, null);

        when(recordRepository.findAll()).thenReturn(List.of(record1, record2));
        when(recordMapper.toDTO(record1)).thenReturn(recordDto1);
        when(recordMapper.toDTO(record2)).thenReturn(recordDto2);

        assertEquals(List.of(recordDto1, recordDto2), recordService.getAll());
    }

    @Test
    void getByIdTestSuccess() {
        var recordId = 1L;

        var record = this.getRecordEntity(recordId, null, null);
        var recordDto = this.getRecordDto(recordId, null);

        when(recordRepository.findById(recordId)).thenReturn(Optional.of(record));
        when(recordMapper.toDTO(record)).thenReturn(recordDto);

        assertEquals(recordDto, recordService.getById(recordId));
    }

    @Test
    void getByIdTestFailed() {
        when(recordRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RecordException.class, () -> recordService.getById(1L));

        verifyNoInteractions(recordMapper);
    }

    @Test
    void getTimingsByMasterAndDateTestSuccess() {
        var masterId = 1L;
        var localDate = LocalDate.now().plusDays(1);

        var timing1 = "09:00-10:00";
        var timing2 = "10:00-11:00";

        var expectedSet = DateTimeUtils.DAILY_SET.stream()
                .filter(e -> !(e.equals(timing1) || e.equals(timing2)))
                .collect(Collectors.toSet());

        var master = this.getMasterEntity(masterId);

        var record1 = this.getRecordEntity(1L, timing1, master);
        var record2 = this.getRecordEntity(2L, timing2, master);

        when(masterRepository.findById(masterId)).thenReturn(Optional.of(master));
        when(recordRepository.findAllByMasterAndDate(master, localDate)).thenReturn(List.of(record1, record2));

        assertEquals(expectedSet, new HashSet<>(recordService.getTimingsByMasterAndDate(masterId, localDate)));
    }

    @Test
    void getTimingsByMasterAndDateTestSuccessNoRecords() {
        var masterId = 1L;
        var localDate = LocalDate.now().plusDays(1);

        var master = this.getMasterEntity(masterId);

        when(masterRepository.findById(masterId)).thenReturn(Optional.of(master));
        when(recordRepository.findAllByMasterAndDate(master, localDate)).thenReturn(Collections.emptyList());

        assertEquals(DateTimeUtils.DAILY_SET, new HashSet<>(recordService.getTimingsByMasterAndDate(masterId, localDate)));
    }

    @Test
    void getTimingsByMasterAndDateTestSuccessEmptyList() {
        var masterId = 1L;
        var localDate = LocalDate.now().plusDays(1);

        var master = this.getMasterEntity(masterId);

        when(masterRepository.findById(masterId)).thenReturn(Optional.of(master));
        when(recordRepository.findAllByMasterAndDate(master, localDate)).thenReturn(this.getFullRecordList(master));

        assertEquals(Collections.emptySet(), new HashSet<>(recordService.getTimingsByMasterAndDate(masterId, localDate)));
    }

    @Test
    void getTimingsByMasterAndDateTestFailedByMaster() {
        var masterId = 1L;
        var localDate = LocalDate.now();

        when(masterRepository.findById(masterId)).thenReturn(Optional.empty());

        assertThrows(MasterNotFoundException.class, () -> recordService.getTimingsByMasterAndDate(masterId, localDate));
    }

    @Test
    void confirmTestSuccess() {
        var recordId = 1L;

        var record = this.getRecordEntity(recordId, null, null);
        var recordDto = this.getRecordDto(recordId, null);

        when(recordMapper.toEntity(recordDto)).thenReturn(record);
        when(recordRepository.save(record)).then(invocation -> invocation.getArgument(0));
        when(recordMapper.toDTO(record)).thenReturn(recordDto);

        assertEquals(recordDto, recordService.confirm(recordDto));
    }

    @Test
    void confirmTestFailed() {
        var recordId = 1L;

        var record = this.getRecordEntity(recordId, null, null);
        var recordDto = this.getRecordDto(recordId, null);

        when(recordMapper.toEntity(recordDto)).thenReturn(record);
        when(recordRepository.save(record)).thenReturn(null);

        assertThrows(OtherException.class, () -> recordService.confirm(recordDto));

        verify(recordMapper, times(0)).toDTO(record);
    }

    private Record getRecordEntity(Long id, String timing, Master master) {
        var record = new Record();
        record.setId(id);
        record.setTiming(timing);
        record.setMaster(master);
        return record;
    }

    private RecordDto getRecordDto(Long id, String timing) {
        var recordDto = new RecordDto();
        recordDto.setId(id);
        recordDto.setTiming(timing);
        return recordDto;
    }

    private Master getMasterEntity(Long id) {
        var master = new Master();
        master.setId(id);
        return master;
    }

    private List<Record> getFullRecordList(Master master) {
        List<Record> recordList = new ArrayList<>();

        recordList.add(this.getRecordEntity(1L, "09:00-10:00", master));
        recordList.add(this.getRecordEntity(2L, "10:00-11:00", master));
        recordList.add(this.getRecordEntity(3L, "11:00-12:00", master));
        recordList.add(this.getRecordEntity(4L, "12:00-13:00", master));
        recordList.add(this.getRecordEntity(5L, "13:00-14:00", master));
        recordList.add(this.getRecordEntity(6L, "14:00-15:00", master));
        recordList.add(this.getRecordEntity(7L, "15:00-16:00", master));
        recordList.add(this.getRecordEntity(8L, "16:00-17:00", master));
        recordList.add(this.getRecordEntity(9L, "17:00-18:00", master));
        recordList.add(this.getRecordEntity(10L, "18:00-19:00", master));
        recordList.add(this.getRecordEntity(11L, "19:00-20:00", master));
        recordList.add(this.getRecordEntity(12L, "20:00-21:00", master));

        return recordList;
    }

}