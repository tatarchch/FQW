package com.example.fqw.controller;


import com.example.fqw.api.PlaceControllerInterface;
import com.example.fqw.dto.PlaceDto;
import com.example.fqw.service.PlaceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@SecurityRequirement(name = "basicAuth")
public class PlaceControllerImpl implements PlaceControllerInterface {

    private final PlaceService placeService;

    @Override
    public List<PlaceDto> getAll() {
        return placeService.getAll();
    }

    @Override
    public PlaceDto addNewPlace(PlaceDto placeDto) {
        return placeService.addNew(placeDto);
    }

    @Override
    public PlaceDto getPlaceById(Long id) {
        return placeService.getPlaceById(id);
    }

    @Override
    public void deletePlaceById(Long id) {
        placeService.deletePlaceById(id);
    }

    /*@PostMapping("/pickPlace/{placeId}")
    public PreOrderDto pickPlace(@PathVariable("placeId") Long placeId, @RequestBody PreOrderDto preOrderDto){
        return placeService.pickPlace(placeId, preOrderDto);
    }*/

    /*@GetMapping("/getById/{id}")
    public PlaceDto getById(@PathVariable("id") Long id) {
        return placeService.getById(id);
    }*/

}
