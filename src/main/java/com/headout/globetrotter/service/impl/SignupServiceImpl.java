package com.headout.globetrotter.service.impl;

import com.headout.globetrotter.dto.request.SignUpRequest;
import com.headout.globetrotter.dto.response.SignUpResponse;
import com.headout.globetrotter.entity.Users;
import com.headout.globetrotter.repository.UsersRepository;
import com.headout.globetrotter.service.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public SignUpResponse signUpUser(SignUpRequest request) {
        try {
            // Validate inputs
            if (request.getEmail() == null || request.getUsername() == null || request.getPassword() == null) {
                log.warn("Missing required fields");
                return SignUpResponse.builder()
                        .message("Missing required fields")
                        .registered(false)
                        .email(request.getEmail())
                        .build();
            }

            // Check if email or username already exists
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                log.warn("User already exists with email: {}", request.getEmail());
                return SignUpResponse.builder()
                        .message("User already exists with this email")
                        .registered(false)
                        .email(request.getEmail())
                        .build();
            }

            if (userRepository.findByUsername(request.getUsername()).isPresent()) {
                log.warn("User already exists with username: {}", request.getUsername());
                return SignUpResponse.builder()
                        .message("User already exists with this username")
                        .registered(false)
                        .email(request.getEmail())
                        .build();
            }

            Users newUser = Users.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .score(0)
                    .userGuesses(new ArrayList<>())
                    .build();

            log.info("Saving user to database");
            userRepository.save(newUser);
            log.info("User saved successfully");

            return SignUpResponse.builder()
                    .message("User signed up successfully")
                    .registered(true)
                    .email(request.getEmail())
                    .build();
        } catch (Exception e) {
            log.error("Error during sign-up: ", e);  // Log the full stack trace
            return SignUpResponse.builder()
                    .message("Failed to sign up user: " + e.getMessage())
                    .registered(false)
                    .email(request.getEmail())
                    .build();
        }
    }

}
