package com.example.fqw.service;

import com.example.fqw.dto.ClientDto;
import com.example.fqw.entity.Client;
import com.example.fqw.enums.RolesEnum;
import com.example.fqw.exception.ClientAlreadyExistsException;
import com.example.fqw.exception.ClientNotFoundException;
import com.example.fqw.mapper.ClientMapperImpl;
import com.example.fqw.repositories.ClientRepository;
import com.example.fqw.services.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapperImpl clientMapper;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private ClientService clientService;

    @Test
    void getAllUsersTestSuccess() {

        var client1 = this.getClientEntity(null, "login1", "password1", null, null);
        var client2 = this.getClientEntity(null, "login2", "password2", null, null);
        var client3 = this.getClientEntity(null, "login3", "password3", null, null);

        var clientDto1 = this.getClientDto(null, "login1", "password1", null, null);
        var clientDto2 = this.getClientDto(null, "login2", "password2", null, null);
        var clientDto3 = this.getClientDto(null, "login3", "password3", null, null);

        when(clientRepository.findAllByRole(RolesEnum.USER.getRole())).thenReturn(List.of(client1, client2, client3));
        when(clientMapper.toDTO(any(Client.class))).thenCallRealMethod();

        assertIterableEquals(List.of(clientDto1, clientDto2, clientDto3), clientService.getAllUsers());

    }

    @Test
    void getAllUsersTestFailed() {
        when(clientRepository.findAllByRole(any(String.class))).thenReturn(Collections.emptyList());

        assertTrue(clientService.getAllUsers().isEmpty());
    }

    @Test
    void getClientByIdTestSuccess() {
        var clientId = 1L;

        var client = this.getClientEntity(clientId, null, null, null, null);
        var clientDto = this.getClientDto(clientId, null, null, null, null);

        when(clientRepository.findByIdAndRole(clientId, RolesEnum.USER.getRole())).thenReturn(Optional.of(client));
        when(clientMapper.toDTO(client)).thenCallRealMethod();

        assertEquals(clientDto, clientService.getClientById(clientId));
    }

    @Test
    void getClientByIdTestFailed() {
        var id = 1L;

        when(clientRepository.findByIdAndRole(id, RolesEnum.USER.getRole())).thenReturn(Optional.empty());
        verifyNoInteractions(clientMapper);

        assertThrows(ClientNotFoundException.class, () -> clientService.getClientById(id));
    }

    @Test
    void getClientByLoginTestSuccess() {
        var login = "login";

        var client = this.getClientEntity(null, login, null, null, null);
        var clientDto = this.getClientDto(null, login, null, null, null);

        when(clientRepository.findClientByLoginAndRole(login, RolesEnum.USER.getRole())).thenReturn(Optional.of(client));
        when(clientMapper.toDTO(client)).thenCallRealMethod();

        assertEquals(clientDto, clientService.getClientByLogin(login));
    }

    @Test
    void getClientByLoginTestFailed() {
        var login = "login";

        when(clientRepository.findClientByLoginAndRole(login, RolesEnum.USER.getRole())).thenReturn(Optional.empty());

        verifyNoInteractions(clientMapper);
        verify(clientMapper, times(0)).toDTO(any(Client.class));

        assertThrows(ClientNotFoundException.class, () -> clientService.getClientByLogin(login));
    }

    @Test
    void getClientByLoginAndPasswordSuccess() {
        var login = "login";
        var password = "password";

        var client = this.getClientEntity(null, login, password, null, null);
        var expectedClientDto = this.getClientDto(null, login, password, null, null);

        when(clientRepository.findClientByLoginAndRole(login,RolesEnum.USER.getRole())).thenReturn(Optional.of(client));
        when(encoder.matches(client.getPassword(), password)).thenReturn(true);
        when(clientMapper.toDTO(client)).thenReturn(expectedClientDto);

        assertEquals(expectedClientDto, clientService.getClientByLoginAndPassword(login, password));
    }

    @Test
    void getClientByLoginAndPasswordTestFailed() {

        when(clientRepository.findClientByLoginAndRole(any(String.class), any()))
                .thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientService.getClientByLoginAndPassword("login", "password"));
    }

    @Test
    void registrationTestSuccess() {
        var clientDto = this.getClientDto(null, "login", "password", null, null);

        when(clientRepository.existsClientByLogin(any(String.class))).thenReturn(false);
        when(clientMapper.toEntity(any(ClientDto.class))).thenCallRealMethod();
        when(encoder.encode(clientDto.getPassword())).thenReturn(clientDto.getPassword());
        when(clientRepository.save(any(Client.class))).then(invocation -> invocation.getArgument(0));
        when(clientMapper.toDTO(any(Client.class))).thenCallRealMethod();

        assertEquals(clientDto, clientService.registration(clientDto));
    }

    @Test
    void registrationTestFailed() {
        var clientDto = this.getClientDto(null, "login", "password", null, null);

        when(clientRepository.existsClientByLogin(any(String.class))).thenReturn(true);

        assertThrows(ClientAlreadyExistsException.class, () -> clientService.registration(clientDto));

        verifyNoInteractions(clientMapper);
    }

    @Test
    void botLoginTestFound() {
        var userName = "userName";
        var chatId = "1L";

        var client = this.getClientEntity(null, null, null, userName, chatId);

        var clientDto = this.getClientDto(null, null, null, userName, chatId);

        when(clientRepository.findClientByChatId(chatId)).thenReturn(Optional.of(client));
        when(clientMapper.toDTO(client)).thenCallRealMethod();

        assertEquals(clientDto, clientService.botLogin(chatId, userName));
    }

    @Test
    void botLoginTestNew() {
        var userName = "userName";
        var chatId = "1L";

        var clientDto = this.getClientDto(null, null, null, userName, chatId);

        when(clientRepository.findClientByChatId(chatId)).thenReturn(Optional.empty());
        when(clientRepository.save(any(Client.class))).then(invocation -> invocation.getArgument(0));
        when(clientMapper.toDTO(any(Client.class))).thenCallRealMethod();

        assertEquals(clientDto, clientService.botLogin(chatId, userName));
    }

    private Client getClientEntity(Long clientId,
                                   String login,
                                   String password,
                                   String name,
                                   String chatId) {
        var client = new Client();
        client.setId(clientId);
        client.setLogin(login);
        client.setPassword(password);
        client.setName(name);
        client.setChatId(chatId);
        return client;
    }

    private ClientDto getClientDto(Long clientId,
                                   String login,
                                   String password,
                                   String name,
                                   String chatId) {
        var clientDto = new ClientDto();
        clientDto.setId(clientId);
        clientDto.setLogin(login);
        clientDto.setPassword(password);
        clientDto.setName(name);
        clientDto.setChatId(chatId);
        return clientDto;
    }

}