package com.example.fqw.mapper;

import com.example.fqw.dto.*;
import com.example.fqw.entity.*;
import com.example.fqw.entity.Record;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, PlaceMapper.class, PetServiceMapper.class, MasterMapper.class})
public interface RecordMapper {

    @Mapping(target = "client", source = "clientDto")
    @Mapping(target = "petService", source = "petServiceDto")
    @Mapping(target = "master", source = "masterDto")
    @Mapping(target = "status", constant = "created")
    Record toEntity(RecordDto recordDto);

    @Mapping(target = "clientDto", source = "record.client")
    @Mapping(target = "petServiceDto", source = "record.petService")
    @Mapping(target = "masterDto", source = "record.master")
    @Mapping(target = "placeDto", source = "master.place")
    RecordDto toDTO(Record record);

    /*@Named("clientToDto")
    default ClientDto clientToDto(Client client) {
        return Mappers.getMapper(ClientMapper.class).toDTO(client);
    }

    @Named("placeToDto")
    default PlaceDto placeToDto(Place place) {
        return Mappers.getMapper(PlaceMapper.class).toDTO(place);
    }

    @Named("petServiceToDto")
    default PetServiceDto petServiceToDto(PetService petService) {
        return Mappers.getMapper(PetServiceMapper.class).toDTO(petService);
    }

    @Named("masterToDto")
    default MasterDto masterToDto(Master master) {
        return Mappers.getMapper(MasterMapper.class).toDTO(master);
    }*/

}
