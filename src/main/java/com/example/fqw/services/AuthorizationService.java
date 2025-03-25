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
public class AuthorizationService /*implements AuthenticationManager*/ /*implements UserDetailsService*/ {

    private final ClientRepository clientRepository;
    //private final AdminRepository adminRepository;
    private final PasswordEncoder encoder;

    //private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /*private final AuthenticationManager authenticationManager;*/

    /*@Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return adminRepository.findAdminByLogin(login)
                .map(admin -> User.builder().username(admin.getLogin()).password("{noop}" + admin.getPassword()).roles("ADMIN").build())
                .orElseGet(() -> clientRepository.findClientByLogin(login)
                        .map(client -> User.builder().username(client.getLogin()).password("{noop}" + client.getPassword()).roles("USER").build())
                        .orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким логином не найден")));
    }*/

    /*public UsernamePasswordAuthenticationToken authentication(String login, String password) { //pass s fronta
        return adminRepository.findAdminByLoginAndPassword(login, password)
                .map(admin -> User.builder().username(admin.getLogin()).password("{noop}" + admin.getPassword()).roles("ADMIN").build())
                .or(() -> clientRepository.findClientByLoginAndPassword(login, password)
                        .map(client -> User.builder().username(client.getLogin()).password("{noop}" + client.getPassword()).roles("USER").build()))
                .map(user -> new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        null,
                        user.getAuthorities()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Неверный логин или пароль"));
    }*/

    /*public UsernamePasswordAuthenticationToken authentication(String login, String password) { //pass s fronta
        return adminRepository.findAdminByLogin(login)
                .filter(a -> encoder.matches(password, a.getPassword()))
                .map(admin -> User.builder().username(admin.getLogin()).password(admin.getPassword()).roles("ADMIN").build())
                .or(() -> clientRepository.findClientByLogin(login)
                        .filter(c -> encoder.matches(password, c.getPassword()))
                        .map(client -> User.builder().username(client.getLogin()).password(client.getPassword()).roles("USER").build()))
                .map(user -> new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        null,
                        user.getAuthorities()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Неверный логин или пароль"));
    }*/

    public UsernamePasswordAuthenticationToken authentication(String login, String password) { //pass s fronta
        return clientRepository.findClientByLogin(login)
                .filter(client -> encoder.matches(password, client.getPassword()))
                .map(client -> User.builder().username(client.getLogin()).password(client.getPassword()).roles(client.getRole()).build())
                .map(user -> new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        null,
                        user.getAuthorities()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Неверный логин или пароль"));
    }

    /*@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return Optional.of(authentication.getCredentials().toString())
                .map(JwtUtils::validateJwtToken)
                .map(jwt -> {
                    var sub = jwt.getSubject();
                    var role = List.of(new SimpleGrantedAuthority("ROLE_" + jwt.get("role")));
                    return new UsernamePasswordAuthenticationToken(sub, null, role);
                }
                )
                .orElseThrow();
    }*/
}
