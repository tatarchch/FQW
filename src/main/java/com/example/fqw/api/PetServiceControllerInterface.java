package com.example.fqw.api;

import com.example.fqw.dto.PetServiceDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/service")
@Validated
public interface PetServiceControllerInterface {

    @GetMapping("/getAll")
    List<PetServiceDto> getAllServices();

    @GetMapping("/getByMasterLevel/{masterLevel}")
    List<PetServiceDto> getServicesByMasterLevel(@PathVariable("masterLevel")
                                                 @NotNull(message = "Уровень мастера не может быть null")
                                                 @Positive(message = "Уровень мастера должен быть положительным")
                                                 @Min(value = 1, message = "Уровень мастера не может быть меньше 1")
                                                 @Max(value = 3, message = "Уровень мастера не может быть выше 3")
                                                 Long masterLevel);


    @GetMapping("/getById/{serviceId}")
    PetServiceDto getServiceById(@PathVariable("serviceId")
                                 @NotNull(message = "Id услуги не может быть null")
                                 @Positive(message = "Id услуги должен быть положительным")
                                 Long serviceId);

    @PostMapping("/addNew")
    PetServiceDto addNewService(@Valid
                                @RequestBody
                                PetServiceDto petServiceDto);

    @DeleteMapping("/deleteById/{serviceId}")
    void deleteServiceById(@PathVariable("serviceId")
                           @NotNull(message = "Id услуги не может быть null")
                           @Positive(message = "Id услуги должен быть положительным")
                           Long serviceId);
}
