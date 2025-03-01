package com.headout.globetrotter.service.impl;

import com.headout.globetrotter.dto.request.AnswerSubmission;
import com.headout.globetrotter.dto.response.AnswerWithScore;
import com.headout.globetrotter.dto.response.Options;
import com.headout.globetrotter.dto.response.Question;
import com.headout.globetrotter.entity.Clues;
import com.headout.globetrotter.entity.Place;
import com.headout.globetrotter.entity.Facts;
import com.headout.globetrotter.entity.UserGuesses;
import com.headout.globetrotter.entity.Users;
import com.headout.globetrotter.jwt.AuthTokenFilter;
import com.headout.globetrotter.repository.*;
import com.headout.globetrotter.service.QuizService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

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

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private FactsRepository factsRepository;

    @Override
    public Question getQuizQuestion() {
        try {
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

            return new Question(clue.getId(), clue.getClue(), options); // Include clueId

        } catch (Exception e) {
            log.error("Error fetching quiz question", e);
            throw new RuntimeException("Failed to fetch quiz question", e);
        }
    }

    @Override
    @Transactional
    public AnswerWithScore submitAnswer(AnswerSubmission submission) {
        try {
            log.info("Processing submission for user: {} and clue: {}", authTokenFilter.userSessionDetails(), submission.getClueId());

            // Fetch clue
            Clues clue = clueRepository.findById(submission.getClueId())
                    .orElseThrow(() -> {
                        log.error("Clue with ID {} not found", submission.getClueId());
                        return new RuntimeException("Clue not found");
                    });

            // Find correct place from Clue
            Place correctPlace = clue.getPlace();
            Integer correctPlaceId = correctPlace.getId();
            log.info("Correct place for the clue: {}", correctPlaceId);

            // Fetch the correct place name (correct answer)
            String correctAnswer = correctPlace.getName();

            // Check if user guessed correctly
            boolean isCorrect = correctPlaceId.equals(submission.getGuessedPlaceId());
            log.info("User guessed: {}, Correct: {}", submission.getGuessedPlaceId(), isCorrect);

            // Fetch user
            String loggedUserEmail = authTokenFilter.userSessionDetails();
            Users user = usersRepository.findByUsername(loggedUserEmail)
                    .orElseThrow(() -> {
                        log.error("User with email {} not found", loggedUserEmail);
                        return new RuntimeException("User not found");
                    });

            // Award points (3 for correct, 0 for incorrect)
            int scoreAwarded = isCorrect ? 3 : 0;

            // Update user's score if correct
            if (isCorrect) {
                user.setScore(user.getScore() + scoreAwarded);
                usersRepository.save(user);
                log.info("Updated score for user {}: {}", user.getId(), user.getScore());
            }

            // Get the updated total score
            int totalScore = user.getScore();

            // Fetch facts related to the correct place
            List<Facts> factsList = factsRepository.findByPlaceId(correctPlaceId);
            String fact = (factsList != null && !factsList.isEmpty())
                    ? factsList.get(new Random().nextInt(factsList.size())).getFact()
                    : "No fact available for this place.";

            // Log the guess
            UserGuesses guess = UserGuesses.builder()
                    .user(user)
                    .place(correctPlace)
                    .guessedPlace(new Place(submission.getGuessedPlaceId()))
                    .isCorrect(isCorrect)
                    .score(scoreAwarded)
                    .guessedAt(new Timestamp(new Date().getTime()))
                    .build();

            userGuessesRepository.save(guess);
            log.info("Guess recorded for user {}: {}", user.getId(), guess);

            // Return response with correct answer, fact, and score details
            return AnswerWithScore.builder()
                    .isCorrect(isCorrect)
                    .correctPlaceId(correctPlaceId)
                    .correctAnswer(correctAnswer) // Include correct answer
                    .fact(fact) // Include random fact
                    .scoreAwarded(scoreAwarded)
                    .userScore(totalScore)
                    .build();

        } catch (Exception e) {
            log.error("Error processing submission: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to process submission");
        }
    }


}




