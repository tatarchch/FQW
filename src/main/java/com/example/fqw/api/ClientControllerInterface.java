package com.example.fqw.api;

import com.example.fqw.dto.ClientDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/client")
@Validated
public interface ClientControllerInterface {

    @GetMapping("/login")
    ClientDto login(@RequestParam("login")
                    @NotNull(message = "login клиента не может быть null")
                    String login,
                    @RequestParam("password")
                    @NotNull(message = "password клиента не может быть null")
                    String password);

    @PostMapping("/registration")
    ClientDto registration(@Valid
                           @RequestBody
                           ClientDto clientDto);

    @GetMapping("/getAll")
    List<ClientDto> getAllClients();

    @GetMapping("/getById/{clientId}")
    ClientDto getClientById(@PathVariable("clientId")
                            @NotNull(message = "Id клиента не может быть null")
                            @Positive(message = "Id клиента должно быть положительным")
                            Long clientId);

    @GetMapping("/getByLogin/{clientLogin}")
    ClientDto getClientByLogin(@PathVariable("clientLogin")
                               @NotNull(message = "login клиента не может быть null")
                               String login);

    @GetMapping("/getByLoginAndPassword")
    ClientDto getClientByLoginAndPassword(@RequestParam("login")
                                          @NotNull(message = "login клиента не может быть null")
                                          String login,
                                          @RequestParam("password")
                                          @NotNull(message = "password клиента не может быть null")
                                          String password);

    @GetMapping("/getByName/{name}")
    ClientDto getClientByName(@PathVariable("name")
                              @NotBlank(message = "Имя клиента не может быть пустым")
                              String name);

}
