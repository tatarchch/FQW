package com.example.FQW.controller;


import com.example.FQW.dto.RecordDto;
import com.example.FQW.entity.Record;
import com.example.FQW.mapper.RecordMapper;
import com.example.FQW.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/record")
public class RecordController {

    private final RecordService recordService;

    private final RecordMapper recordMapper;

    @GetMapping("/records")
    public List<Record> getAllRecord() {
        return recordService.getAll();
    }

    @GetMapping("/recordssss")
    public List<RecordDto> getAllRecordd() {
        return recordService.getAll().stream()
                .map(recordMapper::toDTO)
                .toList();
    }

    @GetMapping("/recordById/{recordId}")
    public Record getAllRecord(@PathVariable("recordId") Long recordId) {
        return recordService.getById(recordId);
    }

    @PostMapping("/recordDeleteById/{recordId}")
    public void deleteRecordById(@PathVariable("recordId") Long recordId) {
        recordService.cancelRecordById(recordId);
    }
}
