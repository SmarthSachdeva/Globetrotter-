package com.headout.globetrotter.service.impl;

import com.headout.globetrotter.dto.request.AnswerSubmission;
import com.headout.globetrotter.dto.response.AnswerWithScore;
import com.headout.globetrotter.dto.response.Options;
import com.headout.globetrotter.dto.response.Question;
import com.headout.globetrotter.entity.Clues;
import com.headout.globetrotter.entity.Place;
import com.headout.globetrotter.entity.UserGuesses;
import com.headout.globetrotter.entity.Users;
import com.headout.globetrotter.repository.CluesRepository;
import com.headout.globetrotter.repository.PlaceRepository;
import com.headout.globetrotter.repository.UserGuessesRepository;
import com.headout.globetrotter.repository.UsersRepository;
import com.headout.globetrotter.service.QuizService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private UsersRepository usersRepository ;

    @Autowired
    private UserGuessesRepository userGuessesRepository;

    @Autowired
    private CluesRepository clueRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public Question getQuizQuestion() {

        try{
            log.info("Fetching question initiated");
            Clues clue = clueRepository.findRandomClue();

            if (clue == null) {
                throw new RuntimeException("Clue not found");
            }
            // Fetch the correct place
            Place correctPlace = clue.getPlace();

            // Fetch 3 random wrong answers
            List<Place> wrongPlaces = placeRepository.findRandomWrongAnswers(correctPlace.getId());

            // Prepare options
            List<Options> options = new ArrayList<>();

            options.add(Options.builder()
                    .id(correctPlace.getId())
                    .name(correctPlace.getName())
                    .isCorrect(true)
                    .build());

            for (Place place : wrongPlaces) {
                options.add(Options.builder()
                        .id(place.getId())
                        .name(place.getName())
                        .isCorrect(false)
                        .build());
            }

            // Shuffle options to randomize order
            Collections.shuffle(options);

            return new Question(clue.getClue(), options);

        } catch (Exception e) {
            log.error("Error");
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public AnswerWithScore submitAnswer(AnswerSubmission submission) {
        try {
            log.info("Processing submission for user: {} and clue: {}", submission.getUserId(), submission.getClueId());

            // Fetch clue
            Clues clue = clueRepository.findById(submission.getClueId())
                    .orElseThrow(() -> {
                        log.error("Clue with ID {} not found", submission.getClueId());
                        return new RuntimeException("Clue not found");
                    });

            // Find correct place from Clue
            Integer correctPlaceId = clue.getPlace().getId();
            log.info("Correct place for the clue: {}", correctPlaceId);

            // Check if user guessed correctly
            boolean isCorrect = correctPlaceId.equals(submission.getGuessedPlaceId());
            log.info("User guessed: {}, Correct: {}", submission.getGuessedPlaceId(), isCorrect);

            // Fetch user
            Users user = usersRepository.findById(submission.getUserId())
                    .orElseThrow(() -> {
                        log.error("User with ID {} not found", submission.getUserId());
                        return new RuntimeException("User not found");
                    });

            // Award points (3 for correct, -1 for incorrect)
            int scoreAwarded = isCorrect ? 3 : -1;

            // Update user's score if correct
            if (isCorrect) {
                user.setScore(user.getScore() + scoreAwarded);
                usersRepository.save(user);
                log.info("Updated score for user {}: {}", user.getId(), user.getScore());
            }

            // Log the guess
            UserGuesses guess = UserGuesses.builder()
                    .user(user)
                    .place(clue.getPlace())
                    .guessedPlace(new Place(submission.getGuessedPlaceId()))
                    .isCorrect(isCorrect)
                    .score(scoreAwarded)
                    .guessedAt(new Timestamp(new Date().getTime()))
                    .build();

            userGuessesRepository.save(guess);
            log.info("Guess recorded for user {}: {}", user.getId(), guess);

            return AnswerWithScore.builder()
                    .isCorrect(isCorrect)
                    .correctPlaceId(correctPlaceId)
                    .scoreAwarded(scoreAwarded)
                    .build();

        } catch (Exception e) {
            log.error("Error processing submission: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to process submission");
        }
    }
}




