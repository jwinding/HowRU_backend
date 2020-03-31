package com.academy.HowRU.QuestionSet.controllers;

import com.academy.HowRU.QuestionSet.inputModels.QuestionSetInput;
import com.academy.HowRU.QuestionSet.services.QuestionSetInputService;
import com.academy.HowRU.QuestionSet.viewModels.QuestionSetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InputController {

    @Autowired
    private QuestionSetInputService inputService;

    @PostMapping(path="/questionsets", consumes = "application/json;charset=UTF-8")
    public QuestionSetView postNewQuestionSet(@RequestBody QuestionSetInput qsInput){

    }
}
