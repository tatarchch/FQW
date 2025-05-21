package com.example.fqw.services;

import com.example.fqw.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder encoder;

    public UsernamePasswordAuthenticationToken authentication(String login, String password) { //pass s fronta
        return clientRepository.findClientByLogin(login)
                .filter(client -> encoder.matches(password, client.getPassword()))
                .map(client -> User.builder().username(client.getLogin()).password(client.getPassword()).roles(client.getRole()).build())
                .map(user -> new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        null,
                        user.getAuthorities()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Неверный логин '" + login + "' или пароль к нему"));
    }

}
