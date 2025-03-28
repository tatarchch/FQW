package com.example.fqw.services;

import com.example.fqw.dto.MasterDto;
import com.example.fqw.entity.PetService;
import com.example.fqw.exception.MasterAlreadyExistsException;
import com.example.fqw.exception.MasterNotFoundException;
import com.example.fqw.exception.PetServiceNotFoundException;
import com.example.fqw.exception.PlaceNotFoundException;
import com.example.fqw.mapper.MasterMapper;
import com.example.fqw.repositories.MasterRepository;
import com.example.fqw.repositories.PetServiceRepository;
import com.example.fqw.repositories.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterService {

    private final MasterRepository masterRepository;
    private final MasterMapper masterMapper;
    private final PlaceRepository placeRepository;
    private final PetServiceRepository petServiceRepository;

    public List<MasterDto> getAll() {
        return masterRepository.findAll().stream()
                .map(masterMapper::toDTO)
                .toList();
    }

    public MasterDto getById(Long id) {
        return masterRepository.findById(id)
                .map(masterMapper::toDTO)
                .orElseThrow(MasterNotFoundException::new);
    }

    public MasterDto getMasterByName(String name) {
        return masterRepository.findMasterByName(name)
                .map(masterMapper::toDTO)
                .orElseThrow(MasterNotFoundException::new);
    }

    public List<MasterDto> getMastersByPlaceId(Long placeId) {
        return placeRepository.findById(placeId)
                .map(masterRepository::findAllByPlace)
                .map(masters -> masters.stream()
                        .map(masterMapper::toDTO)
                        .toList())
                .orElseThrow(MasterNotFoundException::new);
    }

    public List<MasterDto> getMastersByPlaceIdAndServiceId(Long placeId, Long serviceId) {
        return placeRepository.findById(placeId)
                .map(masterRepository::findAllByPlace)
                .map(masters -> petServiceRepository.findById(serviceId)
                        .map(PetService::getLevel)
                        .map(level -> masters.stream()
                                .filter(service -> service.getLevel() >= level)
                                .toList())
                        .orElseThrow(PetServiceNotFoundException::new))
                .map(master -> master.stream()
                        .map(masterMapper::toDTO)
                        .toList())
                .orElseThrow(PlaceNotFoundException::new);
    }

    public MasterDto addNewMaster(MasterDto masterDto) {
        return Optional.of(masterDto)
                .map(masterMapper::toEntity)
                .map(masterRepository::save)
                .map(masterMapper::toDTO)
                .orElseThrow(MasterAlreadyExistsException::new);
    }

    public MasterDto inactivateMasterById(Long id) {
        return masterRepository.findById(id)
                .map(masterMapper::inactivateMaster)
                .map(masterRepository::save)
                .map(masterMapper::toDTO)
                .orElseThrow(MasterNotFoundException::new);
    }

}
