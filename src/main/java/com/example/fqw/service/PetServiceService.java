package com.example.fqw.service;

import com.example.fqw.dto.PetServiceDto;
import com.example.fqw.entity.Master;
import com.example.fqw.entity.PetService;
import com.example.fqw.exception.MasterException.MasterNotFoundException;
import com.example.fqw.exception.PetServiceException.PetServiceNotFoundException;
import com.example.fqw.mapper.PetServiceMapper;
import com.example.fqw.repositories.MasterRepository;
import com.example.fqw.repositories.PetServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PetServiceService {

    private final PetServiceRepository petServiceRepository;

    private final PetServiceMapper petServiceMapper;

    private final MasterRepository masterRepository;


    public List<PetServiceDto> getAll() {
        return petServiceRepository.findAll().stream()
                .map(petServiceMapper::toDTO)
                .toList();
    }

    public PetServiceDto getById(Long id) {
        return petServiceRepository.findById(id)
                .map(petServiceMapper::toDTO)
                .orElseThrow(PetServiceNotFoundException::new);
    }

    public PetService getServiceById(Long id) {
        return petServiceRepository.findById(id)
                .orElseThrow(PetServiceNotFoundException::new);
    }

    public List<PetServiceDto> getServicesByMasterId(Long masterId) {
        return masterRepository.findById(masterId)
                .map(Master::getLevel)
                .map(petServiceRepository::findAllByLevelLessThanEqual)
                .map(services -> services.stream()
                        .map(petServiceMapper::toDTO)
                        .toList())
                .orElseThrow(MasterNotFoundException::new);
    }

    public PetServiceDto getServiceByName(String name) {
        return petServiceRepository.findServiceByName(name)
                .map(petServiceMapper::toDTO)
                .orElseThrow(PetServiceNotFoundException::new);
    }

    /*public PreOrderDto pickService(Long serviceId, PreOrderDto preOrderDto) {
        return preOrderService.callService(serviceId, preOrderDto);
    }*/

}