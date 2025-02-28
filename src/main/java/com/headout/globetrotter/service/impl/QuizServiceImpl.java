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
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
        // Fetch a random clue
        Clues clue = clueRepository.findRandomClue();
        if (clue == null) throw new RuntimeException("No clues found");

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
    }

    @Override
    @Transactional
    public AnswerWithScore submitAnswer(AnswerSubmission submission) {
        // Fetch clue
        Clues clue = clueRepository.findById(submission.getClueId())
                .orElseThrow(() -> new RuntimeException("Clue not found"));

        // Find correct place from Clue
        Integer correctPlaceId = clue.getPlace().getId();

        // Check if user guessed correctly
        boolean isCorrect = correctPlaceId.equals(submission.getGuessedPlaceId());

        // Fetch user
        Users user = usersRepository.findById(submission.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Award points (10 for correct, 0 for incorrect)
        int scoreAwarded = isCorrect ? 10 : 0;

        // Update user's score
        if (isCorrect) {
            user.setScore(user.getScore() + scoreAwarded);
            usersRepository.save(user);
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

        return AnswerWithScore.builder()
                       .isCorrect(isCorrect)
                       .correctPlaceId(correctPlaceId)
                       .scoreAwarded(scoreAwarded)
                       .build();
    }

}




