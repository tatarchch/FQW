package com.example.FQW.service;

import com.example.FQW.dto.PreOrderDto;
import com.example.FQW.entity.Record;
import com.example.FQW.repositories.RecordRepository;
import com.example.FQW.response.exception.OtherException;
import com.example.FQW.response.exception.RecordException.RecordException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Setter
@Getter
@AllArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    private final ClientService clientService;
    private final CalendarService calendarService;
    private final MasterService masterService;
    private final PlaceService placeService;
    private final ServiceService serviceService;

    private final PreOrderService preOrderService;


    public List<Record> getAll() {
        return recordRepository.findAll();
    }

    public Record getById(Long id) {
        return recordRepository.findById(id).orElseThrow(RecordException::new);
    }

    public Record createRecord(PreOrderDto preOrderDto) {
        return Optional.of(new Record())
                .map(record -> fillRecord(record, preOrderDto))
                .map(recordRepository::save)
                .orElseThrow(OtherException::new);
    }

    public Record cancelRecordById(Long id) {
        return recordRepository.findById(id)
                .map(this::fillCanceledRecord)
                .map(recordRepository::save)
                .orElseThrow(OtherException::new);
    }


    public Record doneRecordById(Long id) {
        return recordRepository.findById(id)
                .map(this::fillCanceledRecord)
                .map(recordRepository::save)
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

    private Record fillRecord(Record record, PreOrderDto preOrderDto){
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
