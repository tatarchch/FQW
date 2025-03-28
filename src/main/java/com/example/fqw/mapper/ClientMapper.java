package com.example.fqw.mapper;


import com.example.fqw.dto.ClientDto;
import com.example.fqw.entity.Client;
import org.mapstruct.Mapper;

@Mapper
public interface ClientMapper {

    Client toEntity(ClientDto clientDto);

    ClientDto toDTO(Client client);

}
