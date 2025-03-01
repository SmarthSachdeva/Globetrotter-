package com.headout.globetrotter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserScoreResponse {
    private String username;
    private Integer score;
}

