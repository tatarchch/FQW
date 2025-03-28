package com.example.fqw.services;

import com.example.fqw.dto.RecordDto;
import com.example.fqw.entity.Record;
import com.example.fqw.exception.MasterNotFoundException;
import com.example.fqw.exception.OtherException;
import com.example.fqw.exception.RecordException;
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

    public List<String> getTimingsByMasterAndDate(Long masterId, LocalDate date) {
        return masterRepository.findById(masterId)
                .map(master -> recordRepository.findAllByMasterAndDate(master, date).stream()
                        .map(Record::getTiming)
                        .toList())
                .map(timingList -> DateTimeUtils.getFreeTimingList(timingList, date))
                .orElseThrow(MasterNotFoundException::new);
    }

    public RecordDto confirm(RecordDto recordDto) {
        return Optional.of(recordDto)
                .map(recordMapper::toEntity)
                .map(recordRepository::save)
                .map(recordMapper::toDTO)
                .orElseThrow(RecordException::new);
    }

    public RecordDto addNewRecord(RecordDto recordDto) {
        return Optional.of(recordDto)
                .map(recordMapper::toEntity)
                .map(recordRepository::save)
                .map(recordMapper::toDTO)
                .orElseThrow(OtherException::new);
    }

}
