package com.example.fqw.service;

import com.example.fqw.repositories.AdminRepository;
import com.example.fqw.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return adminRepository.findAdminByLogin(login)
                .map(admin -> User.builder().username(admin.getLogin()).password("{noop}" + admin.getPassword()).roles("ADMIN").build())
                .orElseGet(() -> clientRepository.findClientByLogin(login)
                        .map(client -> User.builder().username(client.getLogin()).password("{noop}" + client.getPassword()).roles("USER").build())
                        .orElseThrow(() -> new UsernameNotFoundException("пользователь с таким логином не найден")));
    }
}
