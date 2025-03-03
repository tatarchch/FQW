package com.example.fqw.api;

import com.example.fqw.dto.PetServiceDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/service")
public interface PetServiceControllerInterface {

    @GetMapping("/getAll")
    List<PetServiceDto> getAllServices();

    @GetMapping("/getByMasterLevel/{masterLevel}")
    List<PetServiceDto> getServicesByMasterId(@PathVariable("masterLevel") Long masterLevel);


    @GetMapping("/getById/{serviceId}")
    PetServiceDto getServiceById(@PathVariable("serviceId") Long id);

    @PostMapping("/addNew")
    PetServiceDto addNewService(@RequestBody PetServiceDto petServiceDto);

    @DeleteMapping("/deleteById/{serviceId}")
    void deleteServiceById(@PathVariable("serviceId") Long id);
}
