package com.example.fqw.api;

import com.example.fqw.dto.MasterDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/master")
@Validated
public interface MasterControllerInterface {

    @GetMapping("/getMastersByPlaceId/{placeId}")
    List<MasterDto> getMastersByPlaceId(@PathVariable("placeId")
                                        @Positive(message = "Id места должен быть положительным")
                                        Long placeId);

    @GetMapping("/getMastersByPlaceIdAndServiceLevel")
    List<MasterDto> getMastersByPlaceIdAndServiceId(@RequestParam("placeId")
                                                    @Positive(message = "Id места должен быть положительным")
                                                    Long placeId,
                                                    @RequestParam("serviceId")
                                                    @Positive(message = "Id услуги должен быть положительным")
                                                    Long serviceId);


    @GetMapping("/getAll")
    List<MasterDto> getAllMaster();

    @GetMapping("/getById/{masterId}")
    MasterDto getMasterById(@PathVariable("masterId")
                            @Positive(message = "Id мастера должен быть положительным")
                            Long masterId);

    @PostMapping("/addNew")
    MasterDto addNewMaster(@Valid
                           @RequestBody
                           MasterDto masterDto);

    @DeleteMapping("/deleteById/{masterId}")
    void deleteMasterById(@PathVariable("masterId")
                          @Positive(message = "Id мастера должен быть положительным")
                          Long masterId);

}
