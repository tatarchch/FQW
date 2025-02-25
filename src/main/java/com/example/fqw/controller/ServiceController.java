package com.example.fqw.controller;


import com.example.fqw.dto.*;
import com.example.fqw.service.PetServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/service")
public class ServiceController {

    private final PetServiceService petServiceService;

    @GetMapping("/services")
    public List<PetServiceDto> getAllServices() {
        return petServiceService.getAll();
    }


    @GetMapping("getServicesByMasterLevel/{masterLevel}")
    public List<PetServiceDto> getServicesByMasterId(@PathVariable("masterLevel") Long masterLevel) {
        return petServiceService.getServicesByMasterId(masterLevel);
    }

    /*@PostMapping("/pickService/{serviceId}")
    public PreOrderDto pickService(@PathVariable("serviceId") Long serviceId, @RequestBody PreOrderDto preOrderDto) {
        return serviceService.pickService(serviceId, preOrderDto);
    }*/

    /* @GetMapping("/serviceById/{serviceId}")
    public ServiceDto getServiceById(@PathVariable("serviceId") Long serviceId) {
        return serviceService.getById(serviceId);
    }


    @GetMapping("/placesById/{serviceId}")
    public List<PlaceDto> getAllPlaceById(@PathVariable("serviceId") Long serviceId) {
        return serviceService.getAllPlaceById(serviceId);
    }

    @GetMapping("/calendarsById/{serviceId}")
    public List<CalendarDto> getAllCalendarById(@PathVariable("serviceId") Long serviceId) {
        return serviceService.getAllCalendarById(serviceId);
    }

    @GetMapping("/mastersById/{serviceId}")
    public List<MasterDto> getAllMasterById(@PathVariable("serviceId") Long serviceId) {
        return serviceService.getAllMasterById(serviceId);
    }*/

}
