package com.example.fqw.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.util.Base64;

@UtilityClass
public class AuthUtils {
    public String getBasicAuth(Authentication authentication) {
        String login = ((User) authentication.getPrincipal()).getUsername();
        String password = (String) authentication.getCredentials();
        String auth = login.concat(":").concat(password);
        byte[] encodedBytes = Base64.getEncoder().encode(auth.getBytes());
        return "Basic ".concat(new String(encodedBytes));
    }
}
