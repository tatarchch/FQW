package com.example.fqw.service;

import com.example.fqw.dto.PlaceDto;
import com.example.fqw.entity.Place;
import com.example.fqw.exception.PlaceException.PlaceAlreadyExistsException;
import com.example.fqw.exception.PlaceException.PlaceNotFoundException;
import com.example.fqw.mapper.PlaceMapperImpl;
import com.example.fqw.repositories.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaceServiceTest {
    @Mock
    private PlaceRepository placeRepository;
    @Mock
    private PlaceMapperImpl placeMapper;

    @InjectMocks
    private PlaceService placeService;

    @Test
    void getAllTest() {
        var place1 = this.getPlaceEntity(1L, null);
        var place2 = this.getPlaceEntity(2L, null);

        var placeDto1 = this.getPlaceDto(1L, null);
        var placeDto2 = this.getPlaceDto(2L, null);

        when(placeRepository.findAll()).thenReturn(List.of(place1, place2));
        when(placeMapper.toDTO(any(Place.class))).thenCallRealMethod();

        assertEquals(List.of(placeDto1, placeDto2), placeService.getAll());
    }

    @Test
    void getByIdTestSuccess() {
        var placeId = 1L;

        var place = this.getPlaceEntity(placeId, null);

        var placeDto = this.getPlaceDto(placeId, null);

        when(placeRepository.findById(placeId)).thenReturn(Optional.of(place));
        when(placeMapper.toDTO(place)).thenCallRealMethod();

        assertEquals(placeDto, placeService.getById(placeId));
    }

    @Test
    void getByIdTestFailed() {
        when(placeRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(PlaceNotFoundException.class, () -> placeService.getById(1L));

        verifyNoInteractions(placeMapper);
    }

    @Test
    void addNewTestSuccess() {
        var placeDto = this.getPlaceDto(1L, null);

        when(placeMapper.toEntity(placeDto)).thenCallRealMethod();
        when(placeRepository.save(any(Place.class))).then(invocation -> invocation.getArgument(0));
        when(placeMapper.toDTO(any(Place.class))).thenCallRealMethod();

        assertEquals(placeDto, placeService.addNew(placeDto));
    }

    @Test
    void addNewTestFailed() {
        var placeDto = this.getPlaceDto(1L, null);

        when(placeMapper.toEntity(placeDto)).thenCallRealMethod();
        when(placeRepository.save(any(Place.class))).thenThrow(PlaceAlreadyExistsException.class);

        assertThrows(PlaceAlreadyExistsException.class, () -> placeService.addNew(placeDto));
    }

    @Test
    void getPlaceByNameTestSuccess() {
        var placeName = "dom";

        var place = this.getPlaceEntity(1L, placeName);

        var placeDto = this.getPlaceDto(1L, placeName);

        when(placeRepository.findPlaceByName(placeName)).thenReturn(Optional.of(place));
        when(placeMapper.toDTO(place)).thenCallRealMethod();

        assertEquals(placeDto, placeService.getPlaceByName(placeName));
    }

    @Test
    void getPlaceByNameTestFailed() {
        when(placeRepository.findPlaceByName(any(String.class))).thenReturn(Optional.empty());

        assertThrows(PlaceNotFoundException.class, () -> placeService.getPlaceByName(""));

        verifyNoInteractions(placeMapper);
    }


    private Place getPlaceEntity(Long placeId, String placeName) {
        var place = new Place();
        place.setId(placeId);
        place.setName(placeName);
        return place;
    }

    private PlaceDto getPlaceDto(Long placeId, String placeName) {
        var placeDto = new PlaceDto();
        placeDto.setId(placeId);
        placeDto.setName(placeName);
        return placeDto;
    }
}