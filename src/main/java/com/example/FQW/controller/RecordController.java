package com.example.FQW.controller;


import com.example.FQW.dto.RecordDto;
import com.example.FQW.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/record")
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/records")
    public List<RecordDto> getAllRecord() {
        return recordService.getAll();
    }

    @PostMapping("/add")
    public RecordDto addRecord(@RequestBody RecordDto recordDto) {
        return recordService.addRecord(recordDto);
    }

    @PostMapping("/confirm")
    public RecordDto confirmRecord(@RequestBody RecordDto recordDto) {
        return recordService.confirm(recordDto);
    }

    @PostMapping("/getDailyByMasterAndDate/{masterId}")
    public List<String> getDailyByMasterAndDate(@PathVariable("masterId") Long masterId, LocalDate date) {
        return recordService.getDailyByMasterAndDate(masterId, date);
    }


    /*@GetMapping("/recordById/{recordId}")
    public RecordDto getAllRecord(@PathVariable("recordId") Long recordId) {
        return recordService.getById(recordId);
    }*/

    /*@PostMapping("/recordDeleteById/{recordId}")
    public void deleteRecordById(@PathVariable("recordId") Long recordId) {
        recordService.cancelRecordById(recordId);
    }*/
}
