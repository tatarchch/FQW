package com.example.fqw.api;

import com.example.fqw.dto.RecordDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/record")
public interface RecordControllerInterface {

    @GetMapping("/getAll")
    List<RecordDto> getAllRecord();

    @GetMapping("/getById/{recordId}")
    RecordDto getRecordById(@PathVariable("recordId") Long id);

    @PostMapping("/addNew")
    RecordDto addNewRecord(@RequestBody RecordDto recordDto);

    @GetMapping("/getTimingsByMasterAndDate/{masterId}")
    List<String> getTimingsByMasterAndDate(@PathVariable("masterId") Long masterId, @RequestParam String date);

}
