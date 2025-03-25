package com.example.fqw.controllers;

import com.example.fqw.api.AuthControllerInterface;
import com.example.fqw.security.JwtResponse;
import com.example.fqw.security.LoginRequest;
import com.example.fqw.services.AuthorizationService;
import com.example.fqw.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthControllerInterface {

    private final AuthorizationService authorizationService;

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authorizationService.authentication(loginRequest.getUsername(), loginRequest.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

}
