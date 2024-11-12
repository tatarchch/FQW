package com.example.FQW.mapper;


import com.example.FQW.dto.ClientDto;
import com.example.FQW.entity.Client;
import org.mapstruct.Mapper;

@Mapper
public interface ClientMapper {

    Client toEntity(ClientDto clientDto);

    ClientDto toDTO(Client client);
}
