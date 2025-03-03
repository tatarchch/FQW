package com.example.fqw.api;

import com.example.fqw.dto.RecordDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/record")
public interface RecordControllerInterface {

    @GetMapping("/getAll")
    List<RecordDto> getAllRecord();

    @GetMapping("/getById/{recordId}")
    RecordDto getRecordById(@PathVariable("recordId") Long id);

    @PostMapping("/addNew")
    RecordDto addNewRecord(@RequestBody RecordDto recordDto);

    /*@PostMapping("/confirm")
    RecordDto confirmRecord(@RequestBody RecordDto recordDto);*/

    @GetMapping("/getTimingsByMasterAndDate/{masterId}")
    List<String> getTimingsByMasterAndDate(@PathVariable("masterId") Long masterId, @RequestParam String date);

}
