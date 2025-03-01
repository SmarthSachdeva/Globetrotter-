package com.headout.globetrotter.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SignUpResponse {

    private String message;
    private Boolean registered;
    private String email;
}