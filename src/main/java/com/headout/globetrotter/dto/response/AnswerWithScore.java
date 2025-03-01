package com.headout.globetrotter.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerWithScore {
    private boolean isCorrect;
    private Integer correctPlaceId;
    private int scoreAwarded;
}
