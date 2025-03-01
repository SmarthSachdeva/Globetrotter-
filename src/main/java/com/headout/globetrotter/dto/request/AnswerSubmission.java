package com.headout.globetrotter.dto.request;

import lombok.Data;

@Data
public class AnswerSubmission {
    private Integer userId;
    private Integer clueId;
    private Integer guessedPlaceId;
}
