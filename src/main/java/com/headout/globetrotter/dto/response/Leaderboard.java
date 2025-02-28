package com.headout.globetrotter.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Leaderboard {
    private int rank;
    private int userId;
    private String username;
    private int score;

}
