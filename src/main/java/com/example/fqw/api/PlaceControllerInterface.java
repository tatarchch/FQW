package com.example.fqw.api;

import com.example.fqw.dto.PlaceDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/v1/place")
public interface PlaceControllerInterface {

    @GetMapping("/getAll")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    List<PlaceDto> getAll();


    @PostMapping("/addNew")
    //@PreAuthorize("hasRole('ADMIN'")
    PlaceDto addNewPlace(PlaceDto placeDto);

    @GetMapping("/getById/{placeId}")
    //@PreAuthorize("hasRole('ADMIN'")
    PlaceDto getPlaceById(@PathVariable("placeId") Long id);

    @DeleteMapping("/deleteById/{placeId}")
    //@PreAuthorize("hasRole('ADMIN'")
    void deletePlaceById(@PathVariable("placeId") Long id);

}
