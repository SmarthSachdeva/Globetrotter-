package com.headout.globetrotter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CORSConfig {

    // Allowing all origins and methods for now
    @Value("${web.cors.allowed-origins}")
    private List<String> corsUrlList;  // To get URLs from properties (can be modified)

    @Value("${web.cors.allowed-methods}")
    private List<String> corsMethodList;  // Allowed methods

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow all origins for now (you can set this in properties if needed)
        config.addAllowedOrigin("*");  // Allow all origins
        config.addAllowedHeader("*");  // Allow all headers
        config.addAllowedMethod("GET"); // Allow all HTTP methods
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");
        config.setAllowCredentials(true);  // Allow credentials if needed

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
