package com.example.fqw.controller;


import com.example.fqw.dto.MasterDto;
import com.example.fqw.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
