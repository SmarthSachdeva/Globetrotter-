package com.headout.globetrotter.service;


import com.headout.globetrotter.dto.response.LoginResponse;

public interface LoginService {

    LoginResponse loginUser(String email, String password);
}