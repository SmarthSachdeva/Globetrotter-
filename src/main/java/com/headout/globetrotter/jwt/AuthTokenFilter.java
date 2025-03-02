package com.headout.globetrotter.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {


    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        log.info("AuthTokenFilter called for URI : {}", uri);

        // Skip filter for login endpoint
        if (uri.startsWith("/api/v1/users/login") || uri.startsWith("/api/v1/users/signup")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwtToken = parseJwt(request);
            if (jwtToken != null && jwtUtils.validateToken(jwtToken)) {
                String userName = jwtUtils.getUserNameFromToken(jwtToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                // Create the authentication token without authorities
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, null);

                log.info("User authenticated: {}", userName);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("Invalid JWT token : {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {

        return jwtUtils.getJwtFromHeader(request);
    }

    public String userSessionDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
        }
        return null;
    }

}

