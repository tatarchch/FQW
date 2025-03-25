package com.example.fqw.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@UtilityClass
public class JwtUtils {

    private final String jwtSecret = "aStringYouWantToWriteButItNeedToBeLongestPossible";

    private final int jwtExpirationMs = 600000;

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

    /*public String generateJwtToken(Authentication authentication) {

        //User userPrincipal = (User) authentication.getPrincipal();
        String username = authentication.getPrincipal().toString();
        *//*String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(e -> (SimpleGrantedAuthority) e)
                .map(SimpleGrantedAuthority::getAuthority)
                .orElse("asdfasd");*//*

        return Jwts.builder()
                //.subject(userPrincipal.getUsername())    //.setSubject((userPrincipal.getUsername()))
                .subject(username)//.setSubject((userPrincipal.getUsername()))
                .claim("role", authentication.getAuthorities())
                .issuedAt(new Date()) //.setIssuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs)) //.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SECRET_KEY) //.signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }*/

    public String generateJwtToken(Authentication authentication) {

        String username = authentication.getPrincipal().toString();

        //User userPrincipal = (User) authentication.getPrincipal();

        /*String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(e -> (SimpleGrantedAuthority) e)
                .map(SimpleGrantedAuthority::getAuthority)
                .orElse("");*/

        /*String role = authentication.getAuthorities().stream()
                .filter(SimpleGrantedAuthority.class::isInstance)
                .map(SimpleGrantedAuthority.class::cast)
                .map(SimpleGrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No role found"));*/

        List<String> role = authentication.getAuthorities().stream()
                .filter(SimpleGrantedAuthority.class::isInstance)
                .map(SimpleGrantedAuthority.class::cast)
                .map(SimpleGrantedAuthority::getAuthority)
                .toList();


        return Jwts.builder()
                //.subject(userPrincipal.getUsername())    //.setSubject((userPrincipal.getUsername()))
                .subject(username)//.setSubject((userPrincipal.getUsername()))
                .claim("role", role /*authentication.getAuthorities()*/)
                .issuedAt(new Date()) //.setIssuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs)) //.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SECRET_KEY) //.signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    /*private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }*/

    /*private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }*/

    /*public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }*/

    /*public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().verifyWith(SECRET_KEY)
                .build().parseSignedClaims(token).getPayload().getSubject();
    }*/

    /*public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith(SECRET_KEY).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }*/

    public Claims validateJweToken(String authToken) {
        try {
            return Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(authToken)
                    .getPayload();
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw e;
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            throw e;
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            throw e;
        }
    }

    /*public SimpleGrantedAuthority castJwtAuthority(Claims claims) {
        var role = claims.get("role", List.class).stream()
                .findFirst()
                .map(e -> {
                    var map = (LinkedHashMap<?, ?>) e;
                    return map.get("authority");
                }).orElse(claims.getSubject());

        return new SimpleGrantedAuthority(role.toString());
    }*/

    /*public SimpleGrantedAuthority castJwtAuthority(Claims claims) {
        return new SimpleGrantedAuthority(claims.get("role", String.class));
    }*/


    public List<SimpleGrantedAuthority> castJwtAuthority(Claims claims) {
        List<String> roles = claims.get("role", List.class); //вынести в ту утилку и вернуть лист стрингов а не клейм

        List<SimpleGrantedAuthority> simpleRoles = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new ArrayList<>(simpleRoles);
    }

}

