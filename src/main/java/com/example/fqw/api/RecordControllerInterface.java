package com.example.fqw.api;

import com.example.fqw.dto.RecordDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/record")
@Validated
public interface RecordControllerInterface {

    @GetMapping("/getAll")
    List<RecordDto> getAllRecord();

    @GetMapping("/getById/{recordId}")
    RecordDto getRecordById(@PathVariable("recordId")
                            @NotNull(message = "Id записи не может быть null")
                            @Positive(message = "Id записи должно быть положительным")
                            Long recordId);

    @PostMapping("/addNew")
    RecordDto addNewRecord(@Valid
                           @RequestBody
                           RecordDto recordDto);

    @GetMapping("/getTimingsByMasterAndDate/{masterId}")
    List<String> getTimingsByMasterAndDate(@PathVariable("masterId")
                                           @NotNull(message = "Id мастера не может быть null")
                                           @Positive(message = "Id мастера должно быть положительным")
                                           Long masterId,
                                           @RequestParam
                                           @NotBlank(message = "Дата не может быть пустой")
                                           String date);

}
