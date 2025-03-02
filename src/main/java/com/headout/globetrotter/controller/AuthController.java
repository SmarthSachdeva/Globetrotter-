package com.headout.globetrotter.controller;

import com.headout.globetrotter.dto.request.LoginRequest;
import com.headout.globetrotter.dto.request.SignUpRequest;
import com.headout.globetrotter.dto.response.LoginResponse;
import com.headout.globetrotter.dto.response.SignUpResponse;
import com.headout.globetrotter.service.LoginService;
import com.headout.globetrotter.service.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SignupService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(
            @RequestBody SignUpRequest request
    ){
        String userName = request.getUsername();
        String email = request.getEmail();
        String password = request.getPassword();

        log.info("Sign Up Request Received for user : {}" ,userName);
        if(userName == null || email == null || password == null){
            log.error("Incomplete User details");
            return new ResponseEntity<>(SignUpResponse.builder()
                    .email(email)
                    .message("Invalid username or password")
                    .registered(false)
                    .build()
                    , HttpStatus.BAD_REQUEST);
        }
        SignUpResponse response = signUpService.signUpUser(request);

        if(Boolean.TRUE.equals(response.getRegistered())){
            log.info("User with user name {} signed up successfully" ,userName);
            return new ResponseEntity<>(response , HttpStatus.OK);
        }
        log.error("Sign Up failed");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        if(email == null || password == null) {
            LoginResponse failedLogin = LoginResponse.builder()
                    .email(email)
                    .message("Email and password are required")
                    .token("NULL")
                    .build();
            return new ResponseEntity<>(failedLogin, HttpStatus.BAD_REQUEST);
        }
        LoginResponse loginResponse = loginService.loginUser(email, password);

        if(loginResponse.getMessage().equals("Incorrect password")){
            return new ResponseEntity<>(loginResponse , HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(loginResponse , HttpStatus.OK);
    }
}
