package com.example.FQW.mapper;


import com.example.FQW.dto.PlaceDto;
import com.example.FQW.entity.Place;
import org.mapstruct.Mapper;

@Mapper
public interface PlaceMapper {

    PlaceDto toDTO(Place place);

    Place toEntity(PlaceDto placeDto);

}
