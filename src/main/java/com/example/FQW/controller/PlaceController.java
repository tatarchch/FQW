package com.example.FQW.controller;


import com.example.FQW.dto.PlaceDto;
import com.example.FQW.dto.PreOrderDto;
import com.example.FQW.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    /*@PostMapping("/pickPlace/{placeId}")
    public PreOrderDto pickPlace(@PathVariable("placeId") Long placeId, @RequestBody PreOrderDto preOrderDto){
        return placeService.pickPlace(placeId, preOrderDto);
    }*/

    /*@PostMapping("/addNewPlace")
    public PlaceDto addNewPlace(PlaceDto placeDto) {
        return placeService.addNew(placeDto);
    }*/

    /*@GetMapping("/getById/{id}")
    public PlaceDto getById(@PathVariable("id") Long id) {
        return placeService.getById(id);
    }*/


}
