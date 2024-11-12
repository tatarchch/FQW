package com.example.FQW.controller;


import com.example.FQW.dto.PlaceDto;
import com.example.FQW.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/place")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/getAll")
    public List<PlaceDto> getAll() {
        return placeService.getAll();
    }

    @GetMapping("/getById")
    public PlaceDto getById(Long id) {
        return placeService.getById(id);
    }

    @PostMapping("/addNewPlace")
    public PlaceDto addNewPlace(PlaceDto placeDto) {
        return placeService.addNew(placeDto);
    }

}
