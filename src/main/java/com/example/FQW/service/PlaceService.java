package com.example.FQW.service;

import com.example.FQW.dto.PlaceDto;
import com.example.FQW.entity.Place;
import com.example.FQW.mapper.PlaceMapper;
import com.example.FQW.repositories.PlaceRepository;
import com.example.FQW.response.exception.PlaceException.PlaceAlreadyExistsException;
import com.example.FQW.response.exception.PlaceException.PlaceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    private final PlaceMapper placeMapper;

    private final PreOrderService preOrderService;

    public List<PlaceDto> getAll() {
        return placeRepository.findAll().stream()
                .map(placeMapper::toDTO)
                .toList();
    }

    public PlaceDto getById(Long id) {
        return placeRepository.findById(id)
                .map(placeMapper::toDTO)
                .orElseThrow(PlaceNotFoundException::new);
    }

    public PlaceDto addNew(PlaceDto placeDto) {
        return Optional.of(placeDto)
                .map(placeMapper::toEntity)
                .map(placeRepository::save)
                .map(placeMapper::toDTO)
                .orElseThrow(PlaceAlreadyExistsException::new);
    }


    public Place getPlaceById(Long id) {
        return placeRepository.findById(id)
                .orElseThrow(PlaceNotFoundException::new);
    }

}
