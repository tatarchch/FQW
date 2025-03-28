package com.example.fqw.controllers;


import com.example.fqw.api.PlaceControllerInterface;
import com.example.fqw.dto.PlaceDto;
import com.example.fqw.services.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
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

}
