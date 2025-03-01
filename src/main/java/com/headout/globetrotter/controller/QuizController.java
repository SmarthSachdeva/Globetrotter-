package com.headout.globetrotter.controller;

import com.headout.globetrotter.dto.request.AnswerSubmission;
import com.headout.globetrotter.dto.response.AnswerWithScore;
import com.headout.globetrotter.dto.response.Question;
import com.headout.globetrotter.service.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping("/clue")
    public ResponseEntity<Question> getQuizQuestion() {
        try{

            log.info("Request received to fetch quiz question");

            return ResponseEntity.ok(quizService.getQuizQuestion());

        } catch (Exception e){

            log.error("Failed to fetch quiz question" , e);

            return ResponseEntity.badRequest().body(null);

        }
    }

//    todo : replace request-body with request headers
//    todo : once jwt is implemented user id should come from there
    @PostMapping("/submit")
    public ResponseEntity<AnswerWithScore> submitAnswer(@RequestBody AnswerSubmission submission) {
        try{

            log.info("Request received to submit answer for quiz question with submission {}", submission);

            return ResponseEntity.ok(quizService.submitAnswer(submission));
        } catch (Exception e) {

            log.error("Failed to submit answer for quiz question with submission {}", submission, e);

            return ResponseEntity.badRequest().body(null);
        }
    }
}
