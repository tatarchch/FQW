package com.example.FQW.controller;


import com.example.FQW.dto.*;
import com.example.FQW.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/service")
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping("/services")
    public List<ServiceDto> getAllServices() {
        return serviceService.getAll();
    }


    @GetMapping("getServicesByMasterLevel/{masterLevel}")
    public List<ServiceDto> getServicesByMasterId(@PathVariable("masterLevel") Long masterLevel) {
        return serviceService.getServicesByMasterId(masterLevel);
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
