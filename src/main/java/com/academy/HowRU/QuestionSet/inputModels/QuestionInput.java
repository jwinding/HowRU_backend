package com.academy.HowRU.QuestionSet.inputModels;

import com.academy.HowRU.QuestionSet.dataModels.responses.ResponseType;
import lombok.Data;

import java.util.List;

@Data
public class QuestionInput {

    private String question;
    private ResponseType type;

    private List<ResponseOptionInput> responses;
}
