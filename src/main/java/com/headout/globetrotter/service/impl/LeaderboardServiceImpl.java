package com.headout.globetrotter.service.impl;

import com.headout.globetrotter.dto.response.Leaderboard;
import com.headout.globetrotter.entity.Users;
import com.headout.globetrotter.repository.UsersRepository;
import com.headout.globetrotter.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardServiceImpl implements LeaderboardService {
    @Autowired
    private UsersRepository userRepository;

    @Override
    public List<Leaderboard> getTopUsers(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Users> topUsers = userRepository.findTopUsers(pageable);

        List<Leaderboard> leaderboard = new ArrayList<>();
        int rank = 1;
        for (Users user : topUsers) {
            leaderboard.add(Leaderboard.builder()
                            .rank(rank++)
                            .userId(user.getId())
                            .username(user.getUsername())
                            .score(user.getScore())
                    .build());
        }

        return leaderboard;
    }
}
