package com.example.FQW.controller;


import com.example.FQW.dto.PreOrderDto;
import com.example.FQW.dto.RecordDto;
import com.example.FQW.service.RecordService;
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
    public RecordDto addRecord(@RequestBody PreOrderDto preOrderDto) {
        return recordService.addRecord(preOrderDto);
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
