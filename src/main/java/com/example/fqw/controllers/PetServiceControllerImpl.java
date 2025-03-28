package com.example.fqw.controllers;


import com.example.fqw.api.PetServiceControllerInterface;
import com.example.fqw.dto.PetServiceDto;
import com.example.fqw.services.PetServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

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

}
