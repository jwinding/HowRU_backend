package com.academy.HowRU.QuestionSet.inputModels;

import com.academy.HowRU.QuestionSet.dataModels.responses.ResponseType;
import lombok.Data;

@Data
public class ResponseOptionInput {

    private ResponseType type;
    private Integer value;
    private String option;
    private String text;
    private Integer min;
    private Integer max;
    private String min_description;
    private String max_description;
}
