package com.example.FQW.mapper;

import com.example.FQW.dto.*;
import com.example.FQW.entity.*;
import com.example.FQW.entity.Record;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecordMapper {

    Record toEntity(RecordDto recordDto);

    @Mapping(target = "clientDto", source = "record.client", qualifiedByName = "clientToDto")
    @Mapping(target = "calendarDto", source = "record.calendar", qualifiedByName = "calendarToDto")
    @Mapping(target = "placeDto", source = "record.place", qualifiedByName = "placeToDto")
    @Mapping(target = "serviceDto", source = "record.service", qualifiedByName = "serviceToDto")
    @Mapping(target = "masterDto", source = "record.master", qualifiedByName = "masterToDto")
    RecordDto toDTO(Record record);

    @Named("clientToDto")
    default ClientDto clientToDto(Client client) {
        return Mappers.getMapper(ClientMapper.class).toDTO(client);
    }

    @Named("calendarToDto")
    default CalendarDto calendarToDto(Calendar calendar) {
        return Mappers.getMapper(CalendarMapper.class).toDTO(calendar);
    }

    @Named("placeToDto")
    default PlaceDto placeToDto(Place place) {
        return Mappers.getMapper(PlaceMapper.class).toDTO(place);
    }

    @Named("serviceToDto")
    default ServiceDto serviceToDto(Service service) {
        return Mappers.getMapper(ServiceMapper.class).toDTO(service);
    }

    @Named("masterToDto")
    default MasterDto masterToDto(Master master) {
        return Mappers.getMapper(MasterMapper.class).toDTO(master);
    }

}
