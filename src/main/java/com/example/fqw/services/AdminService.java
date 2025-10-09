package com.example.fqw.services;

import com.example.fqw.dto.ClientDto;
import com.example.fqw.enums.RolesEnum;
import com.example.fqw.exception.OtherException;
import com.example.fqw.mapper.ClientMapper;
import com.example.fqw.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final PasswordEncoder encoder;


    @Cacheable(value = "admin")
    public List<ClientDto> getAllAdmins() {
        return clientRepository.findAllByRole(RolesEnum.ADMIN.getRole()).stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    @CachePut(value = "admin")
    public ClientDto addNewAdmin(ClientDto clientDto) {
        return Optional.of(clientDto)
                .map(dto -> {
                    dto.setRole(RolesEnum.ADMIN.getRole());
                    return dto;
                })
                .map(clientMapper::toEntity)
                .map(client -> {
                    client.setPassword(encoder.encode(client.getPassword()));
                    return client;
                })
                .map(clientRepository::save)
                .map(clientMapper::toDTO)
                .orElseThrow(OtherException::new);
    }

}
