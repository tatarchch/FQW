package com.example.FQW.controller;


import com.example.FQW.dto.MasterDto;
import com.example.FQW.dto.PreOrderDto;
import com.example.FQW.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/master")
public class MasterController {

    private final MasterService masterService;

    @GetMapping("/masters")
    public List<MasterDto> getAllMaster() {
        return masterService.getAll();
    }

    @GetMapping("/getMastersByPlaceId/{placeId}")
    public List<MasterDto> getMastersByPlaceId(@PathVariable("placeId") Long placeId) {
        return masterService.getMastersByPlaceId(placeId);
    }

    @GetMapping("/getMastersByPlaceIdAndServiceLevel/{placeId}/{serviceId}")
    public List<MasterDto> getMastersByPlaceIdAndServiceLevel(@PathVariable("placeId") Long placeId, @PathVariable("serviceId") Long serviceId) {
        return masterService.getMastersByPlaceIdAndServiceId(placeId, serviceId);
    }

    /*@PostMapping("/pickMaster/{masterId}")
    public PreOrderDto pickMaster(@PathVariable("masterId") Long masterId, @RequestBody PreOrderDto preOrderDto) {
        return masterService.pickMaster(masterId, preOrderDto);
    }*/

    /*@PostMapping("/addNewMaster")
    public MasterDto addNewMaster(MasterDto masterDto) {
        return masterService.addNew(masterDto);
    }*/

    /*@GetMapping("/masterById/{masterId}")
    public MasterDto getMasterById(@PathVariable("masterId") Long masterID) {
        return masterService.getById(masterID);
    }*/

}
