package com.headout.globetrotter.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginResponse {

    private String message;
    private String email;
    private String token;
}