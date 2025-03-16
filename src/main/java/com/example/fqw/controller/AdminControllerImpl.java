package com.example.fqw.controller;

import com.example.fqw.api.AdminControllerInterface;
import com.example.fqw.dto.AdminDto;
import com.example.fqw.service.AdministrationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class AdminControllerImpl implements AdminControllerInterface {

    private final AdministrationService administrationService;

    @Override
    public List<AdminDto> getAllAdmins() {
        return administrationService.getAllAdmins();
    }

    @Override
    public AdminDto addNewAdmin(AdminDto adminDto) {
        return administrationService.addNewAdmin(adminDto);
    }
}
