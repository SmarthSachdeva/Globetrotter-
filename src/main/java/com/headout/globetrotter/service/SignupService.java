package com.headout.globetrotter.service;

import com.headout.globetrotter.dto.request.SignUpRequest;
import com.headout.globetrotter.dto.response.SignUpResponse;

public interface SignupService {

    SignUpResponse signUpUser(SignUpRequest request);
}