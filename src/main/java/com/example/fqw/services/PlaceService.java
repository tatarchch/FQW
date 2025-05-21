package com.example.fqw.services;

import com.example.fqw.dto.PlaceDto;
import com.example.fqw.exception.PlaceAlreadyExistsException;
import com.example.fqw.exception.PlaceNotFoundException;
import com.example.fqw.mapper.PlaceMapper;
import com.example.fqw.repositories.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
                .orElseThrow(() -> new PlaceNotFoundException(id));
    }

    public PlaceDto addNew(PlaceDto placeDto) {
        return Optional.of(placeDto)
                .map(placeMapper::toEntity)
                .map(placeRepository::save)
                .map(placeMapper::toDTO)
                .orElseThrow(() -> new PlaceAlreadyExistsException(placeDto));
    }

    public PlaceDto getPlaceByName(String name) {
        return placeRepository.findPlaceByName(name)
                .map(placeMapper::toDTO)
                .orElseThrow(() -> new PlaceNotFoundException(name));
    }

    public void deletePlaceById(Long id) {
        placeRepository.deleteById(id);
    }

}
