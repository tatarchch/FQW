package com.example.FQW.service;

import com.example.FQW.dto.RecordDto;
import com.example.FQW.entity.Record;
import com.example.FQW.exception.RecordException.OtherException;
import com.example.FQW.exception.RecordException.RecordException;
import com.example.FQW.mapper.RecordMapper;
import com.example.FQW.repositories.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final RecordMapper recordMapper;
    private final MasterService masterService;


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

    public List<String> getDailyByMasterAndDate(Long masterId, LocalDate date) {
        return Optional.of(masterService.getMasterById(masterId))
                .map(master -> recordRepository.findAllByMasterAndDate(master, date).stream()
                        .map(Record::getTiming)
                        .toList())
                .orElseThrow(RecordException::new);
    }

    public RecordDto confirm(RecordDto recordDto) {
        return Optional.of(recordDto)
                .map(recordMapper::toEntity)
                /*.map(record -> {
                    record.setStatus("created");
                    return record;
                })*/
                .map(recordRepository::save)
                .map(recordMapper::toDTO)
                .orElseThrow(RecordException::new);
    }

    //?
    public RecordDto addRecord(RecordDto recordDto) {
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
