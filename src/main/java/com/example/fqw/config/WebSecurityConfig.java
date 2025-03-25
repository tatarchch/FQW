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

    //private final AuthenticationManager authenticationManager;

    //private final AuthorizationService authorizationService; //userDetailsService

    private final AuthEntryPointJwt authEntryPointJwt;

    private final AccessDeniedHandlerJwt accessDeniedHandlerJwt;

    private final AuthTokenFilter authTokenFilter;

    //private final AuthenticationConfiguration authenticationConfiguration;

    /*@Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> authentication;
    }*/

    /*@PostConstruct
    public void eraseCredentials() {
        authenticationManagerBuilder.eraseCredentials(false);
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                //.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                //.authenticationManager(authorizationService)

                .exceptionHandling(e -> e.authenticationEntryPoint(authEntryPointJwt).accessDeniedHandler(accessDeniedHandlerJwt))

                /*.exceptionHandling(e -> e.authenticationEntryPoint((request, response, authException)
                        -> response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)))*/
                /*.exceptionHandling(e -> e.accessDeniedHandler((request, response, accessDeniedException)
                        -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized")))*/
                //.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/api/v1/client/login",
                                "/api/v1/client/registration",
                                "/api/auth/singIn")
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
                        .hasAnyRole("USER", "ADMIN")
                        //.hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        //admin only
                        .requestMatchers("api/v1/**").hasRole("ADMIN") /*.hasRole("ADMIN")*/
                )

                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    /*@Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }*/

    /*@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }*/

    /*@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }
}
