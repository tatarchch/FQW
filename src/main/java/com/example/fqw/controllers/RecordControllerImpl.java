package com.example.fqw.controllers;


import com.example.fqw.api.RecordControllerInterface;
import com.example.fqw.dto.RecordDto;
import com.example.fqw.services.RecordService;
import com.example.fqw.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecordControllerImpl implements RecordControllerInterface {

    private final RecordService recordService;

    @Override
    public List<RecordDto> getAllRecord() {
        return recordService.getAll();
    }

    @Override
    public RecordDto getRecordById(Long id) {
        return recordService.getById(id);
    }

    @Override
    public RecordDto addNewRecord(RecordDto recordDto) {
        return recordService.addNewRecord(recordDto);
    }

    @Override
    public List<String> getTimingsByMasterAndDate(Long masterId, String date) {
        return recordService.getTimingsByMasterAndDate(masterId, DateTimeUtils.getLocalDateFromString(date));
    }

    /*@DeleteMapping("/recordDeleteById/{recordId}")
    public void deleteRecordById(@PathVariable("recordId") Long recordId) {
        recordService.cancelRecordById(recordId);
    }*/
}
