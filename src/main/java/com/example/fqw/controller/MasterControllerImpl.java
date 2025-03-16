package com.example.fqw.controller;


import com.example.fqw.api.MasterControllerInterface;
import com.example.fqw.dto.MasterDto;
import com.example.fqw.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MasterControllerImpl implements MasterControllerInterface {

    private final MasterService masterService;

    @Override
    public List<MasterDto> getMastersByPlaceId(Long placeId) {
        return masterService.getMastersByPlaceId(placeId);
    }

    @Override
    public List<MasterDto> getMastersByPlaceIdAndServiceLevel(Long placeId, Long serviceId) {
        return masterService.getMastersByPlaceIdAndServiceId(placeId, serviceId);
    }

    @Override
    public List<MasterDto> getAllMaster() {
        return masterService.getAll();
    }

    @Override
    public MasterDto getMasterById(Long id) {
        return masterService.getById(id);
    }

    @Override
    public MasterDto addNewMaster(MasterDto masterDto) {
        return masterService.addNewMaster(masterDto);
    }

    @Override
    public void deleteMasterById(Long id) {
        masterService.inactivateMasterById(id);
    }

}
