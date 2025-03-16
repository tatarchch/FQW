package com.example.fqw.api;

import com.example.fqw.dto.RecordDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/v1/record")
public interface RecordControllerInterface {

    @GetMapping("/getAll")
    //@PreAuthorize("hasRole('ADMIN'")
    List<RecordDto> getAllRecord();

    @GetMapping("/getById/{recordId}")
    //@PreAuthorize("hasRole('ADMIN'")
    RecordDto getRecordById(@PathVariable("recordId") Long id);

    @PostMapping("/addNew")
    //@PreAuthorize("hasRole('ADMIN'")
    RecordDto addNewRecord(@RequestBody RecordDto recordDto);

    /*@PostMapping("/confirm")
    RecordDto confirmRecord(@RequestBody RecordDto recordDto);*/

    @GetMapping("/getTimingsByMasterAndDate/{masterId}")
    //@PreAuthorize("hasRole('ADMIN'")
    List<String> getTimingsByMasterAndDate(@PathVariable("masterId") Long masterId, @RequestParam String date);

}
