package com.headout.globetrotter.service;

import com.headout.globetrotter.dto.response.Leaderboard;

import java.util.List;

public interface LeaderboardService {
    List<Leaderboard> getTopUsers(int limit);
}
