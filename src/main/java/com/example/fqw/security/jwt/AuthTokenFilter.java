package com.example.fqw.security.jwt;

import com.example.fqw.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    //private final JwtUtils jwtUtils;

    //private final AuthorizationService authorizationService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null /*&& JwtUtils.validateJwtToken(jwt)*/) {
                //Authentication authentication = new UsernamePasswordAuthenticationToken(jwt, jwt);
                //authorizationService.authenticate(authentication);

                //Authentication authentication = authorizationService.authenticate(new UsernamePasswordAuthenticationToken(jwt, jwt));

                //String username = JwtUtils.getUserNameFromJwtToken(jwt);

                //Authentication authentication1 = authorizationService.authentication(username);

                Authentication authentication = Optional.of(jwt) //authentication v util
                        .map(JwtUtils::validateJweToken)
                        .map(claims -> new UsernamePasswordAuthenticationToken(claims.getSubject(),
                                null,
                                //List.of(JwtUtils.castJwtAuthority(claims)))
                                JwtUtils.castJwtAuthority(claims)))
                        .orElseThrow();



                /*UserDetails userDetails = authorizationService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());*/
                //authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}

