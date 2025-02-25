package com.example.fqw.controller;


import com.example.fqw.dto.RecordDto;
import com.example.fqw.service.RecordService;
import com.example.fqw.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public List<String> getDailyByMasterAndDate(@PathVariable("masterId") Long masterId, @RequestParam String date) {
        return recordService.getTimingsByMasterAndDate(masterId, DateTimeUtils.getLocalDateFromString(date));
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
