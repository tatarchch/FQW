package com.example.FQW.service;

import com.example.FQW.dto.PreOrderDto;
import com.example.FQW.dto.RecordDto;
import com.example.FQW.entity.Record;
import com.example.FQW.exception.RecordException.OtherException;
import com.example.FQW.exception.RecordException.RecordException;
import com.example.FQW.mapper.RecordMapper;
import com.example.FQW.repositories.RecordRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    private final RecordMapper recordMapper;

    private final ClientService clientService;
    private final CalendarService calendarService;
    private final MasterService masterService;
    private final PlaceService placeService;
    private final ServiceService serviceService;


    public List<RecordDto> getAll() {
        return recordRepository.findAll().stream()
                .map(recordMapper::toDTO)
                .toList();
    }

    public RecordDto getById(Long id) {
        return recordRepository.findById(id)
                .map(recordMapper::toDTO)
                .orElseThrow(RecordException::new);
    }

    //?
    public RecordDto addRecord(PreOrderDto preOrderDto) {
        return Optional.of(new Record())
                .map(record -> fillRecord(record, preOrderDto))
                .map(recordRepository::save)
                .map(recordMapper::toDTO)
                .orElseThrow(OtherException::new);
    }


    public RecordDto cancelRecordById(Long id) {
        return recordRepository.findById(id)
                .map(this::fillCanceledRecord)
                .map(recordRepository::save)
                .map(recordMapper::toDTO)
                .orElseThrow(OtherException::new);
    }


    public RecordDto doneRecordById(Long id) {
        return recordRepository.findById(id)
                .map(this::fillCanceledRecord)
                .map(recordRepository::save)
                .map(recordMapper::toDTO)
                .orElseThrow(OtherException::new);
    }

    private Record fillCreatedRecord(Record record) {
        record.setStatus("создан");
        return record;
    }

    private Record fillCanceledRecord(Record record) {
        record.setStatus("отменён");
        return record;
    }

    private Record fillDoneRecord(Record record) {
        record.setStatus("выполнен");
        return record;
    }

    private Record fillClient(Record record, Long clientId) {
        record.setClient(clientService.getClientById(clientId));
        return record;
    }

    private Record fillCalendar(Record record, Long calendarId) {
        record.setCalendar(calendarService.getCalendarById(calendarId));
        return record;
    }

    private Record fillMaster(Record record, Long masterId) {
        record.setMaster(masterService.getMasterById(masterId));
        return record;
    }

    private Record fillPlace(Record record, Long placeId) {
        record.setPlace(placeService.getPlaceById(placeId));
        return record;
    }

    private Record fillService(Record record, Long serviceId) {
        record.setService(serviceService.getServiceById(serviceId));
        return record;
    }

    private Record fillRecord(Record record, PreOrderDto preOrderDto) {
        fillClient(record, preOrderDto.getClientId());
        fillCalendar(record, preOrderDto.getCalendarId());
        fillMaster(record, preOrderDto.getMasterId());
        fillPlace(record, preOrderDto.getPlaceId());
        fillService(record, preOrderDto.getServiceId());
        //daily i tp
        fillCreatedRecord(record);
        return record;
    }

}
