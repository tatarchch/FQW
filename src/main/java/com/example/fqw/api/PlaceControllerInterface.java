package com.example.fqw.api;

import com.example.fqw.dto.PlaceDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/place")
@Validated
public interface PlaceControllerInterface {

    @GetMapping("/getAll")
    List<PlaceDto> getAll();


    @PostMapping("/addNew")
    PlaceDto addNewPlace(@Valid
                         @RequestBody
                         PlaceDto placeDto);

    @GetMapping("/getById/{placeId}")
    PlaceDto getPlaceById(@PathVariable("placeId")
                          @NotNull(message = "Id места не может быть null")
                          @Positive(message = "Id места должно быть положительным")
                          Long placeId);

    @DeleteMapping("/deleteById/{placeId}")
    void deletePlaceById(@PathVariable("placeId")
                         @Positive(message = "Id места должно быть положительным")
                         @NotNull(message = "Id места не может быть null")
                         Long placeId);

}
