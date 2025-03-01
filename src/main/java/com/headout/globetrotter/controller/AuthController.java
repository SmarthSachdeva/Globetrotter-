package com.headout.globetrotter.controller;

import com.headout.globetrotter.dto.request.SignUpRequest;
import com.headout.globetrotter.dto.response.LoginResponse;
import com.headout.globetrotter.dto.response.SignUpResponse;
import com.headout.globetrotter.service.LoginService;
import com.headout.globetrotter.service.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SignupService signUpService;

    @PostMapping("/register")
    public ResponseEntity<SignUpResponse> signUp(
            @RequestHeader("username") String userName ,
            @RequestHeader("email") String email ,
            @RequestHeader("password") String password
    ){
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
        SignUpRequest request = SignUpRequest.builder()
                .email(email)
                .password(password)
                .username(userName)
                .build();

        SignUpResponse response = signUpService.signUpUser(request);

        if(response.getRegistered()){
            log.info("User with user name {} signed up successfully" ,userName);
            return new ResponseEntity<>(SignUpResponse.builder()
                    .email(email)
                    .message("User registration successful")
                    .registered(true)
                    .build()
                    , HttpStatus.OK);
        }
        log.error("Sign Up failed");
        return new ResponseEntity<>(SignUpResponse.builder()
                .email(email)
                .message("User registration failed")
                .registered(false)
                .build()
                , HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestHeader("email") String email ,
            @RequestHeader("password") String password
    ) {

        if(email == null || password == null) {
            LoginResponse failedLogin = LoginResponse.builder()
                    .email(email)
                    .message("Email and password are required")
                    .token("NULL")
                    .build();
            return new ResponseEntity<>(failedLogin, HttpStatus.BAD_REQUEST);
        }
        LoginResponse successfulLogin = loginService.loginUser(email, password);

        return new ResponseEntity<>(successfulLogin , HttpStatus.OK);
    }
}
