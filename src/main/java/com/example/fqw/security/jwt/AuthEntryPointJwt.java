package com.example.fqw.security.jwt;

import com.example.fqw.exception.ResponseError;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Component
@Slf4j
@RestControllerAdvice
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        log.error("Unauthorized error: {}", authException.getMessage());
        //response.sendError(HttpServletResponse.SC_CONFLICT, authException.getClass().getSimpleName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        /*final Map<String, Object> body = new HashMap<>();
        body.put("status", response.getStatus());
        body.put("error", authException.getClass().getSimpleName());
        body.put("message", authException.getMessage());
        body.put("path", request.getServletPath());*/

        this.mapper.writeValue(response.getOutputStream(),
                new ResponseError(authException.getMessage()));
    }
}
