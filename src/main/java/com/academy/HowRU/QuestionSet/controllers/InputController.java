package com.academy.HowRU.QuestionSet.controllers;

import com.academy.HowRU.QuestionSet.inputModels.QuestionSetInput;
import com.academy.HowRU.QuestionSet.inputModels.QuestionSetInputValidator;
import com.academy.HowRU.QuestionSet.services.QuestionSetInputService;
import com.academy.HowRU.QuestionSet.viewModels.QuestionSetView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Parameter;

@RestController
public class InputController {

    @Autowired
    private QuestionSetInputService inputService;

    private final Logger log = LoggerFactory.getLogger(InputController.class);

    @PostMapping(path="/questionsets", consumes = "application/json;charset=UTF-8")
    public QuestionSetView postNewQuestionSet(@RequestBody QuestionSetInput qsInput, BindingResult result) throws ClassNotFoundException {

        QuestionSetInputValidator validator = new QuestionSetInputValidator();
        validator.validate(qsInput, result);

        if(result.hasErrors()){
            log.error("input validation error", result);
            return QuestionSetView.getEmpty();
        } else {
            var qs = inputService.saveNewQuestionSet(qsInput);
            return QuestionSetView.from(qs);
        }

    }
}
