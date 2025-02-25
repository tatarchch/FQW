package com.example.fqw.mapper;


import com.example.fqw.dto.PlaceDto;
import com.example.fqw.entity.Place;
import org.mapstruct.Mapper;

@Mapper
public interface PlaceMapper {

    PlaceDto toDTO(Place place);

    Place toEntity(PlaceDto placeDto);

}
