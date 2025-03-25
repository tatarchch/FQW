package com.example.fqw.services;

import com.example.fqw.dto.ClientDto;
import com.example.fqw.exception.OtherException;
import com.example.fqw.mapper.ClientMapper;
import com.example.fqw.repositories.ClientRepository;
import com.example.fqw.enums.RolesEnum;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AdminService {

    ClientRepository clientRepository;
    ClientMapper clientMapper;
    PasswordEncoder encoder;

    public List<ClientDto> getAllAdmins() {
        return clientRepository.findAllByRole(RolesEnum.ADMIN.getRole()).stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    /*public AdminDto addNewAdmin(AdminDto adminDto) {
        return Optional.of(adminDto)
                .map(adminMapper::toEntity)
                .map(adminRepository::save)
                .map(adminMapper::toDTO)
                .orElseThrow(OtherException::new);
    }*/

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
