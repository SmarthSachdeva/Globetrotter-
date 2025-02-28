package com.headout.globetrotter.service;

import com.headout.globetrotter.dto.request.AnswerSubmission;
import com.headout.globetrotter.dto.response.AnswerWithScore;
import com.headout.globetrotter.dto.response.Question;

public interface QuizService {
    Question getQuizQuestion() ;

    AnswerWithScore submitAnswer(AnswerSubmission submission);
}
