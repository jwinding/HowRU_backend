package com.academy.HowRU.QuestionSet.inputModels;

import com.academy.HowRU.QuestionSet.dataModels.options.ResponseType;
import lombok.Data;

@Data
public class ResponseOptionInput {

    private ResponseType type;
    private Integer value;
    private String option;
    private String text;
    private Integer min, max;
    private String min_description, max_description;
}
