package com.example.fqw.api;

import com.example.fqw.dto.PlaceDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/place")
public interface PlaceControllerInterface {

    @GetMapping("/getAll")
    List<PlaceDto> getAll();


    @PostMapping("/addNew")
    PlaceDto addNewPlace(PlaceDto placeDto);

    @GetMapping("/getById/{placeId}")
    PlaceDto getPlaceById(@PathVariable("placeId") Long id);

    @DeleteMapping("/deleteById/{placeId}")
    void deletePlaceById(@PathVariable("placeId") Long id);

}
