package com.headout.globetrotter.controller;

import com.headout.globetrotter.dto.response.Leaderboard;
import com.headout.globetrotter.service.LeaderboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {
    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping("/top")
    public ResponseEntity<List<Leaderboard>> getTopUsers(@RequestParam(defaultValue = "10") int limit) {
        try{

            log.info("Leaderboard to get top {} users" ,limit );

            return ResponseEntity.ok(leaderboardService.getTopUsers(limit));
        } catch (Exception e) {
            log.error("Error getting top users", e);
            return ResponseEntity.badRequest().body(null);
        }
    }
}