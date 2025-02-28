package com.headout.globetrotter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Question {
    private String clue;
    private List<Options> options;
}
