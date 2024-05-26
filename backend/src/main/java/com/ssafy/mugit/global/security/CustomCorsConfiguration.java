package com.ssafy.mugit.global.security;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpHeaders.SET_COOKIE;

@Component
public class CustomCorsConfiguration {

    public CorsConfigurationSource getSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // allow server
        configuration.addAllowedOrigin("https://mugit.site");
        // allow local
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("http://localhost:8080");

        configuration.addExposedHeader(SET_COOKIE);
        configuration.addAllowedHeader("*");
        configuration.setAllowedMethods(asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
