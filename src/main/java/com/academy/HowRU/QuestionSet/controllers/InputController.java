package com.academy.HowRU.QuestionSet.controllers;

import com.academy.HowRU.QuestionSet.inputModels.QuestionSetInput;
import com.academy.HowRU.QuestionSet.inputModels.validators.QuestionSetInputValidator;
import com.academy.HowRU.QuestionSet.services.QuestionSetInputService;
import com.academy.HowRU.QuestionSet.services.QuestionSetService;
import com.academy.HowRU.QuestionSet.services.QuestionViewService;
import com.academy.HowRU.errorHandling.exceptions.EntityNotFoundException;
import com.academy.HowRU.errorHandling.exceptions.InputContentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:80"})
public class InputController {

    @Autowired
    private QuestionSetInputService inputService;

    @Autowired
    private QuestionSetService questionSetService;
    @Autowired
    private QuestionViewService questionViewService;

    private final Logger log = LoggerFactory.getLogger(InputController.class);

    @PostMapping(path="/questionset", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Map<String,String>> postNewQuestionSet(@RequestBody QuestionSetInput qsInput, BindingResult result)
            throws InputContentException, EntityNotFoundException {

        URI location= URI.create("/questionsets");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        Map<String,String> res = new HashMap();

        QuestionSetInputValidator validator = new QuestionSetInputValidator();
        validator.validate(qsInput, result);


        if(result.hasErrors()){
            log.error("input validation error", result);
            for(var error:result.getAllErrors()){
                log.error(error.getObjectName() + ": " + error.getCode());
            }
            throw new InputContentException("Invalid content of the post request!",result);
        } else {
            var qs = inputService.saveNewQuestionSet(qsInput);
            log.info("saved question set... id: "+qs.getId() + " ,name: " + qs.getName() + ", creator:" + qs.getCreator().getUsername());

            res.put("id", qs.getId().toString());
            res.put("name", qs.getName());
            res.put("creator", qs.getCreator().getUsername());

            return new ResponseEntity<Map<String, String>>(res,responseHeaders,HttpStatus.CREATED);


        }

    }
}
