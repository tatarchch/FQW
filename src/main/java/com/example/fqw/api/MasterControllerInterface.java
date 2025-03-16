package com.example.fqw.api;

import com.example.fqw.dto.MasterDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/v1/master")
public interface MasterControllerInterface {

    @GetMapping("/getMastersByPlaceId/{placeId}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    List<MasterDto> getMastersByPlaceId(@PathVariable("placeId") Long placeId);

    @GetMapping("/getMastersByPlaceIdAndServiceLevel/{placeId}/{serviceId}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    List<MasterDto> getMastersByPlaceIdAndServiceLevel(@PathVariable("placeId") Long placeId, @PathVariable("serviceId") Long serviceId);


    @GetMapping("/getAll")
    //@PreAuthorize("hasRole('ADMIN'")
    List<MasterDto> getAllMaster();

    @GetMapping("/getById/{masterId}")
    //@PreAuthorize("hasRole('ADMIN'")
    MasterDto getMasterById(@PathVariable("masterId") Long id);

    @PostMapping("/addNew")
    //@PreAuthorize("hasRole('ADMIN'")
    MasterDto addNewMaster(@RequestBody MasterDto masterDto);

    @DeleteMapping("/deleteById/{masterId}")
    //@PreAuthorize("hasRole('ADMIN'")
    void deleteMasterById(@PathVariable("masterId") Long id);

}
