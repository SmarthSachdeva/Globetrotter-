package com.headout.globetrotter.controller;

import com.headout.globetrotter.dto.response.InviteResponse;
import com.headout.globetrotter.dto.response.UserScoreResponse;
import com.headout.globetrotter.entity.Users;
import com.headout.globetrotter.repository.UsersRepository;
import com.headout.globetrotter.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/addfriend")
public class InviteController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/invite/{username}")
    public ResponseEntity<InviteResponse> generateInviteLink(@PathVariable String username) {
        try {
            log.info("Generating invite link for user: {}", username);

            // Fetch user details
            Users user = usersRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Generate invite link
            String inviteLink = Constants.INVITE_LINK + username;
            log.info("Generated invite link: {}", inviteLink);

            return ResponseEntity.ok(new InviteResponse(inviteLink, user.getScore()));
        } catch (Exception e) {
            log.error("Error generating invite link: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new InviteResponse("Error generating invite link", 0));
        }
    }

    @GetMapping("/user/{username}/score")
    public ResponseEntity<UserScoreResponse> getUserScore(@PathVariable String username) {
        try {
            log.info("Fetching score for user: {}", username);

            Users user = usersRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return ResponseEntity.ok(new UserScoreResponse(user.getUsername(), user.getScore()));
        } catch (Exception e) {
            log.error("Error fetching user score: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UserScoreResponse("Error fetching user score", 0));
        }
    }

}
