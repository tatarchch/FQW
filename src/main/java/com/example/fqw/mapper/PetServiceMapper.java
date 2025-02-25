package com.example.fqw.mapper;


import com.example.fqw.dto.PetServiceDto;
import com.example.fqw.entity.PetService;
import org.mapstruct.Mapper;

@Mapper
public interface PetServiceMapper {

    PetService toEntity(PetServiceDto petService);

    PetServiceDto toDTO(PetService petService);

}
