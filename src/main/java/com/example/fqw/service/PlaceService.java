package com.example.fqw.service;

import com.example.fqw.dto.PlaceDto;
import com.example.fqw.exception.PlaceNotFoundException;
import com.example.fqw.mapper.PlaceMapper;
import com.example.fqw.repositories.PlaceRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Optional;

@Service
@RestControllerAdvice
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class PlaceService {

    private final PlaceRepository placeRepository;

    private final PlaceMapper placeMapper;

    public List<PlaceDto> getAll() {
        return placeRepository.findAll().stream()
                .map(placeMapper::toDTO)
                .toList();
    }

    public PlaceDto getPlaceById(Long id) {
        return placeRepository.findById(id)
                .map(placeMapper::toDTO)
                .orElseThrow(PlaceNotFoundException::new);
    }

    public PlaceDto addNew(PlaceDto placeDto) {
        return Optional.of(placeDto)
                .map(placeMapper::toEntity)
                .map(placeRepository::save)
                .map(placeMapper::toDTO)
                .orElseThrow();
    }

    public PlaceDto getPlaceByName(String name) {
        return placeRepository.findPlaceByName(name)
                .map(placeMapper::toDTO)
                .orElseThrow(PlaceNotFoundException::new);
    }

    public void deletePlaceById(Long id) {
        placeRepository.deleteById(id);
    }

    /*public PreOrderDto pickPlace(Long placeId, PreOrderDto preOrderDto) {
        return preOrderService.callPlace(placeId, preOrderDto);
    }*/

}
