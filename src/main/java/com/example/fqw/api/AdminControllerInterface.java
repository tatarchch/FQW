package com.example.fqw.api;

import com.example.fqw.dto.ClientDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/admin")
public interface AdminControllerInterface {

    @GetMapping("/getAll")
    List<ClientDto> getAllAdmins();

    @PostMapping("/addNew")
    ClientDto addNewAdmin(@Valid
                          @RequestBody
                          ClientDto clientDto);
}
