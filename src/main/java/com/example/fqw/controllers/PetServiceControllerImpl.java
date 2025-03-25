package com.example.fqw.controllers;


import com.example.fqw.api.PetServiceControllerInterface;
import com.example.fqw.dto.*;
import com.example.fqw.services.PetServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PetServiceControllerImpl implements PetServiceControllerInterface {

    private final PetServiceService petServiceService;

    @Override
    public List<PetServiceDto> getAllServices() {
        return petServiceService.getAll();
    }

    @Override
    public List<PetServiceDto> getServicesByMasterId(Long masterLevel) {
        return petServiceService.getServicesByMasterId(masterLevel);
    }

    @Override
    public PetServiceDto getServiceById(Long id) {
        return petServiceService.getServiceById(id);
    }

    @Override
    public PetServiceDto addNewService(PetServiceDto petServiceDto) {
        return petServiceService.addNewService(petServiceDto);
    }

    @Override
    public void deleteServiceById(Long id) {
        petServiceService.deleteServiceById(id);
    }


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
