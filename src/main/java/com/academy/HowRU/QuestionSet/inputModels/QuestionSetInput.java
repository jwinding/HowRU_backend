package com.academy.HowRU.QuestionSet.inputModels;


import lombok.Data;

import java.util.List;

@Data
public class QuestionSetInput {

    private String name;
    private String creator;

    private List<QuestionInput> questions;
}
