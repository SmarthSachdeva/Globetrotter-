package com.headout.globetrotter.controller;

import com.headout.globetrotter.dto.request.AnswerSubmission;
import com.headout.globetrotter.dto.response.AnswerWithScore;
import com.headout.globetrotter.dto.response.Question;
import com.headout.globetrotter.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping("/clue")
    public ResponseEntity<Question> getQuizQuestion() {

        return ResponseEntity.ok(quizService.getQuizQuestion());
    }

    @PostMapping("/submit")
    public ResponseEntity<AnswerWithScore> submitAnswer(@RequestBody AnswerSubmission submission) {
        return ResponseEntity.ok(quizService.submitAnswer(submission));
    }
}
