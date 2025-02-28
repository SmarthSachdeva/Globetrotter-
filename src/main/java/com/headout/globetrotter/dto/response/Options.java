package com.headout.globetrotter.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Options {
    private Integer id;
    private String name;
    private boolean isCorrect;
}

