package com.example.FQW.controller;


import com.example.FQW.dto.CalendarDto;
import com.example.FQW.dto.MasterDto;
import com.example.FQW.dto.PlaceDto;
import com.example.FQW.dto.ServiceDto;
import com.example.FQW.service.ServiceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("api/v1/service")
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping("/services")
    public List<ServiceDto> getAllService() {
        return serviceService.getAll();
    }

    @GetMapping("/serviceById/{serviceId}")
    public ServiceDto getServiceById(@PathVariable("serviceId") Long serviceId) {
        return serviceService.getById(serviceId);
    }


    /*@GetMapping("/placesById/{serviceId}")
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
