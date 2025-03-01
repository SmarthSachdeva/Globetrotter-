package com.headout.globetrotter.service.impl;

import com.headout.globetrotter.dto.response.LoginResponse;
import com.headout.globetrotter.entity.Users;
import com.headout.globetrotter.jwt.JwtUtils;
import com.headout.globetrotter.repository.UsersRepository;
import com.headout.globetrotter.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public LoginResponse loginUser(String email, String password) {

        try{
            log.info("Login Request Received for email : {}" , email);
            Users user = userRepository.findByEmail(email).get();

            if(user!=null){
                boolean passwordMatches = BCrypt.checkpw(password, user.getPassword());
                if(passwordMatches){
                    log.info("Successful Login : {}" , email);
                    String jwtToken = jwtUtils.generateToken(user.getUsername());
                    return LoginResponse.builder()
                            .message("Login successful")
                            .email(email)
                            .token(jwtToken)
                            .build();
                }else{
                    log.info("Incorrect Password for email : {}" , email);
                    return LoginResponse.builder()
                            .message("Incorrect password")
                            .email(email)
                            .token(null)
                            .build();
                }

            }else{
                log.info("User Not Found");
                return LoginResponse.builder()
                        .message("User not found")
                        .email(email)
                        .token(null)
                        .build();
            }
        } catch (Exception e) {
            log.error("Error while login user: {}", e.getMessage());
            throw e;
        }
    }
}
