package com.example.fqw.api;

import com.example.fqw.dto.AdminDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/v1/admin")
public interface AdminControllerInterface {

    @GetMapping("/getAll")
    //@PreAuthorize("hasRole('ADMIN')")
    List<AdminDto> getAllAdmins();

    @PostMapping("/addNew")
    //@PreAuthorize("hasRole('ADMIN')")
    AdminDto addNewAdmin(@RequestBody AdminDto adminDto);
}
