package com.example.fqw.controllers;

import com.example.fqw.api.AdminControllerInterface;
import com.example.fqw.dto.ClientDto;
import com.example.fqw.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminControllerInterface {

    private final AdminService adminService;

    @Override
    public List<ClientDto> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @Override
    public ClientDto addNewAdmin(ClientDto clientDto) {
        return adminService.addNewAdmin(clientDto);
    }
}
