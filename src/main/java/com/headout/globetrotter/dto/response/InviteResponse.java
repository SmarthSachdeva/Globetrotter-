package com.headout.globetrotter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InviteResponse {
    private String inviteLink;
    private Integer score;
}

