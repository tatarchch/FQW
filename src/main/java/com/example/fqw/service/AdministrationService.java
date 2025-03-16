package com.example.fqw.service;

import com.example.fqw.dto.AdminDto;
import com.example.fqw.exception.OtherException;
import com.example.fqw.mapper.AdminMapper;
import com.example.fqw.repositories.AdminRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AdministrationService {

    AdminRepository adminRepository;
    AdminMapper adminMapper;

    public List<AdminDto> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(adminMapper::toDTO)
                .toList();
    }

    public AdminDto addNewAdmin(AdminDto adminDto) {
        return Optional.of(adminDto)
                .map(adminMapper::toEntity)
                .map(adminRepository::save)
                .map(adminMapper::toDTO)
                .orElseThrow(OtherException::new);
    }
}
