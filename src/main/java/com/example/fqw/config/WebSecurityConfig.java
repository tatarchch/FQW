package com.example.fqw.config;

import com.example.fqw.security.jwt.AccessDeniedHandlerJwt;
import com.example.fqw.security.jwt.AuthEntryPointJwt;
import com.example.fqw.security.jwt.AuthTokenFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class WebSecurityConfig {

    private final AuthEntryPointJwt authEntryPointJwt;

    private final AccessDeniedHandlerJwt accessDeniedHandlerJwt;

    private final AuthTokenFilter authTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                .exceptionHandling(e -> e.authenticationEntryPoint(authEntryPointJwt).accessDeniedHandler(accessDeniedHandlerJwt))

                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)

                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/api/v1/client/login",
                                "/api/v1/client/registration",
                                "/api/auth/singIn")
                        .permitAll()
                        .requestMatchers(
                                "/api/v1/master/getMastersByPlaceId/{placeId}",
                                "/api/v1/master/getMastersByPlaceIdAndServiceLevel/{placeId}/{serviceId}",
                                "/api/v1/service/getAll",
                                "/api/v1/service/getByMasterLevel/{masterLevel}",
                                "/api/v1/place/getAll"
                        )
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers("api/v1/**").hasRole("ADMIN")
                )

                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }
}
