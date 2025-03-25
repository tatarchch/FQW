package com.example.fqw.api;

import com.example.fqw.dto.ClientDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1/client")
public interface ClientControllerInterface {

    @GetMapping("/login")
    ClientDto login(@RequestParam("login") String login, @RequestParam("password") String password);

    @PostMapping("/registration")
    ClientDto registration(ClientDto clientDto);

    @GetMapping("/getAll")
    List<ClientDto> getAllClients();

    @GetMapping("/getById/{clientId}")
    ClientDto getClientById(@PathVariable("clientId") Long id);

    @GetMapping("/getByLogin/{clientLogin}")
    ClientDto getClientByLogin(@PathVariable("clientLogin") String login);

    @GetMapping("/getByLoginAndPassword/{login}/{password}")
    ClientDto getClientByLoginAndPassword(@PathVariable("login") String login, @PathVariable("password") String password);

    @GetMapping("/getByName/{name}")
    ClientDto getClientByName(@PathVariable("name") String name);

}
