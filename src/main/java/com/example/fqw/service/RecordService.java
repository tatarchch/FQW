package com.example.fqw.service;

import com.example.fqw.dto.RecordDto;
import com.example.fqw.entity.Record;
import com.example.fqw.exception.MasterException.MasterNotFoundException;
import com.example.fqw.exception.OtherException;
import com.example.fqw.exception.RecordException.RecordException;
import com.example.fqw.mapper.RecordMapper;
import com.example.fqw.repositories.MasterRepository;
import com.example.fqw.repositories.RecordRepository;
import com.example.fqw.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final MasterRepository masterRepository;
    private final RecordMapper recordMapper;


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

    /*public List<String> getTimingByMasterAndDate(Long masterId, LocalDate date) {
        return masterRepository.findById(masterId)
                .map(master -> recordRepository.findAllByMasterAndDate(master, date).stream()
                        .map(Record::getTiming)
                        .toList())
                .map(timingList -> DateTimeUtils.getFreeTimingList(timingList, date))
                .orElseThrow(RecordException::new);
    }*/

    public List<String> getTimingsByMasterAndDate(Long masterId, LocalDate date) {
        return masterRepository.findById(masterId)
                .map(master -> recordRepository.findAllByMasterAndDate(master, date).stream()
                        .map(Record::getTiming)
                        .toList())
                .map(timingList -> DateTimeUtils.getFreeTimingList(timingList, date))
                .orElseThrow(MasterNotFoundException::new);
    }

    /*public List<String> getUserChatForNotification() {
        String timing = LocalTime.now().plusHours(1L).getHour() + ":00"
                + "-"
                + LocalTime.now().plusHours(2L).getHour() + ":00";
        return recordRepository
                .findAllByDateAndTiming(LocalDate.now(), timing)
                .stream()
                .map(Record::getClient)
                .map(Client::getChatId)
                .toList();
    }*/

    public RecordDto confirm(RecordDto recordDto) {
        return Optional.of(recordDto)
                .map(recordMapper::toEntity)
                /*.map(record -> {
                    record.setStatus("created"); //did it in the mapper
                    return record;
                })*/
                .map(recordRepository::save)
                .map(recordMapper::toDTO)
                .orElseThrow(RecordException::new);
    }

    //?
    public RecordDto addNewRecord(RecordDto recordDto) {
        return Optional.of(recordDto)
                .map(recordMapper::toEntity)
                .map(recordRepository::save)
                .map(recordMapper::toDTO)
                .orElseThrow(OtherException::new);
    }

    /*public RecordDto cancelRecordById(Long id) {
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
    }*/


}
