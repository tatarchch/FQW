package com.example.fqw.services;

import com.example.fqw.dto.PetServiceDto;
import com.example.fqw.entity.Master;
import com.example.fqw.exception.MasterNotFoundException;
import com.example.fqw.exception.OtherException;
import com.example.fqw.exception.PetServiceNotFoundException;
import com.example.fqw.mapper.PetServiceMapper;
import com.example.fqw.repositories.MasterRepository;
import com.example.fqw.repositories.PetServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    public PetServiceDto getServiceById(Long id) {
        return petServiceRepository.findById(id)
                .map(petServiceMapper::toDTO)
                .orElseThrow(() -> new PetServiceNotFoundException(id));
    }

    public List<PetServiceDto> getServicesByMasterId(Long masterId) {
        return masterRepository.findById(masterId)
                .map(Master::getLevel)
                .map(petServiceRepository::findAllByLevelLessThanEqual)
                .map(services -> services.stream()
                        .map(petServiceMapper::toDTO)
                        .toList())
                .orElseThrow(() -> new MasterNotFoundException(masterId));
    }

    public PetServiceDto getServiceByName(String name) {
        return petServiceRepository.findServiceByName(name)
                .map(petServiceMapper::toDTO)
                .orElseThrow(() -> new PetServiceNotFoundException(name));
    }

    public PetServiceDto addNewService(PetServiceDto petServiceDto) {
        return Optional.of(petServiceDto)
                .map(petServiceMapper::toEntity)
                .map(petServiceRepository::save)
                .map(petServiceMapper::toDTO)
                .orElseThrow(OtherException::new);
    }

    public void deleteServiceById(Long id) {
        petServiceRepository.deleteById(id);
    }

}