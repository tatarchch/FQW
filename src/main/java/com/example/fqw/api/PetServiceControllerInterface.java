package com.example.fqw.api;

import com.example.fqw.dto.PetServiceDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/v1/service")
public interface PetServiceControllerInterface {

    @GetMapping("/getAll")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    List<PetServiceDto> getAllServices();

    @GetMapping("/getByMasterLevel/{masterLevel}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'ROLE_USER')")
    List<PetServiceDto> getServicesByMasterId(@PathVariable("masterLevel") Long masterLevel);


    @GetMapping("/getById/{serviceId}")
    //@PreAuthorize("hasRole('ADMIN'")
    PetServiceDto getServiceById(@PathVariable("serviceId") Long id);

    @PostMapping("/addNew")
    //@PreAuthorize("hasRole('ADMIN'")
    PetServiceDto addNewService(@RequestBody PetServiceDto petServiceDto);

    @DeleteMapping("/deleteById/{serviceId}")
    //@PreAuthorize("hasRole('ADMIN'")
    void deleteServiceById(@PathVariable("serviceId") Long id);
}
