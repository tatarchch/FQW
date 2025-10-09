package com.example.fqw.services;

import com.example.fqw.dto.PlaceDto;
import com.example.fqw.exception.PlaceAlreadyExistsException;
import com.example.fqw.exception.PlaceNotFoundException;
import com.example.fqw.mapper.PlaceMapper;
import com.example.fqw.repositories.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "place"/*, key = "place.id", unless = "#result == null"*/)
    public PlaceDto getPlaceById(Long id) {
        return placeRepository.findById(id)
                .map(placeMapper::toDTO)
                .orElseThrow(() -> new PlaceNotFoundException(id));
    }

    @Cacheable(value = "place"/*, key = "#place.id", unless = "#result == null"*/)
    public PlaceDto getPlaceByName(String name) {
        return placeRepository.findPlaceByName(name)
                .map(placeMapper::toDTO)
                .orElseThrow(() -> new PlaceNotFoundException(name));
    }

    @CachePut(value = "place"/*, key = "#place.id"*/)
    public PlaceDto addNew(PlaceDto placeDto) {
        return Optional.of(placeDto)
                .map(placeMapper::toEntity)
                .map(placeRepository::save)
                .map(placeMapper::toDTO)
                .orElseThrow(() -> new PlaceAlreadyExistsException(placeDto));
    }

    @CacheEvict(value = "place"/*, key = "#place.id"*/)
    public void deletePlaceById(Long id) {
        placeRepository.deleteById(id);
    }

}
