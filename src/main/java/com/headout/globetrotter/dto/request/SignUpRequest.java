package com.headout.globetrotter.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SignUpRequest {

    private String username;
    private String email;
    private String password;
}