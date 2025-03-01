package com.headout.globetrotter.dto.request;

import lombok.Data;

@Data
public class AnswerSubmission {
    private Integer clueId;
    private Integer guessedPlaceId;
}
