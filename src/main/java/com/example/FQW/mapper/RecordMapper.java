package com.example.FQW.mapper;

import com.example.FQW.dto.ClientDto;
import com.example.FQW.dto.RecordDto;
import com.example.FQW.entity.Client;
import com.example.FQW.entity.Record;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecordMapper {

    Record toEntity(RecordDto recordDto);

    @Mapping(target = "clientDto", source = "record.client", qualifiedByName = "clientToDto")
    //@Mapping(target = "clientDto", source = "record.client", qualifiedByName = "clientToDto")
    RecordDto toDTO(Record record);

    @Named("clientToDto")
    default ClientDto clientToDto(Client client) {
        return Mappers.getMapper(ClientMapper.class).toDTO(client);
    }

}
