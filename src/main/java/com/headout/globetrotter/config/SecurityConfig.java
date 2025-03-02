package com.headout.globetrotter.config;

import com.headout.globetrotter.jwt.AuthEntryPointJwt;
import com.headout.globetrotter.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthEntryPointJwt authEntryPointJwt;

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("https://globe-trotter-fe-ornd.vercel.app" , "https://headout-globetrotter.netlify.app/" ,
                "http://headout-globetrotter.netlify.app/" ,"http://globe-trotter-fe-ornd.vercel.app" , "*" )); // Specify the frontend URL
        configuration.setAllowedMethods(List.of("*")); // Allow all methods
        configuration.setAllowedHeaders(List.of("*")); // Allow all headers
        configuration.setExposedHeaders(List.of("*")); // Expose all headers
        configuration.setAllowCredentials(true); // Allow credentials (cookies, headers, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS
                .csrf(csrf -> csrf.disable()) // Disable CSRF for API requests
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow OPTIONS preflight requests
                                .requestMatchers("/api/v1/users/*", "/api/auth/**").permitAll() // Public endpoints
                                .requestMatchers("/favicon.ico").permitAll()
                                .anyRequest().authenticated() // Secure the rest
                )
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(authEntryPointJwt)
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless for JWT
                )
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
