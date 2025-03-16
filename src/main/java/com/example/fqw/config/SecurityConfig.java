package com.example.fqw.config;

import com.example.fqw.utils.AuthUtils;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
//@EnableMethodSecurity
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "basicAuth",
        scheme = "basic")
public class SecurityConfig {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostConstruct
    public void eraseCredentials() {
        authenticationManagerBuilder.eraseCredentials(false);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/swagger-ui.html",
                                        "/api/v1/client/login",
                                        "/api/v1/client/registration")
                                .permitAll()
                                //with user
                                .requestMatchers(
                                        //master
                                        "/api/v1/master/getMastersByPlaceId/{placeId}",
                                        "/api/v1/master/getMastersByPlaceIdAndServiceLevel/{placeId}/{serviceId}",
                                        //service
                                        "/api/v1/service/getAll",
                                        "/api/v1/service/getByMasterLevel/{masterLevel}",
                                        //place
                                        "/api/v1/place/getAll"
                                )
                                .hasAnyRole("ADMIN", "USER")
                                //admin only
                                .requestMatchers(
                                        "/api/v1/admin/**",
                                        "/api/v1/calendar/**",
                                        "/api/v1/client/**",
                                        "/api/v1/master/**",
                                        "/api/v1/service/**",
                                        "/api/v1/place/**",
                                        "/api/v1/record/**"
                                ).hasRole("ADMIN")
                        //.anyRequest().authenticated()
                )

                .httpBasic(basic -> basic.authenticationEntryPoint(
                        (request, response, authException) -> response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)))

                .formLogin(login -> login.successHandler(
                        (request, response, authentication) -> response.setHeader(
                                "Authorization", AuthUtils.getBasicAuth(authentication))))

                //.formLogin(e -> e.loginProcessingUrl("/api/v1/client/login"))

                //.formLogin(AbstractAuthenticationFilterConfigurer::permitAll)

                .build();
    }

}
