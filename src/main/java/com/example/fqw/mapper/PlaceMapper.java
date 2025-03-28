package com.example.fqw.mapper;


import com.example.fqw.dto.PlaceDto;
import com.example.fqw.entity.Place;
import org.mapstruct.Mapper;

@Mapper
public interface PlaceMapper {

    Place toEntity(PlaceDto placeDto);

    PlaceDto toDTO(Place place);

}
