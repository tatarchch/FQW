package com.example.fqw.api;

import com.example.fqw.dto.MasterDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/master")
public interface MasterControllerInterface {

    @GetMapping("/getMastersByPlaceId/{placeId}")
    List<MasterDto> getMastersByPlaceId(@PathVariable("placeId") Long placeId);

    @GetMapping("/getMastersByPlaceIdAndServiceLevel/{placeId}/{serviceId}")
    List<MasterDto> getMastersByPlaceIdAndServiceLevel(@PathVariable("placeId") Long placeId, @PathVariable("serviceId") Long serviceId);


    @GetMapping("/getAll")
    List<MasterDto> getAllMaster();

    @GetMapping("/getById/{masterId}")
    MasterDto getMasterById(@PathVariable("masterId") Long id);

    @PostMapping("/addNew")
    MasterDto addNewMaster(@RequestBody MasterDto masterDto);

    @DeleteMapping("/deleteById/{masterId}")
    void deleteMasterById(@PathVariable("masterId") Long id);

}
