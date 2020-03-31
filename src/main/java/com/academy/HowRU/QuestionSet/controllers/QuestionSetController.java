package com.academy.HowRU.QuestionSet.controllers;

import com.academy.HowRU.QuestionSet.dataModels.QuestionSet;
import com.academy.HowRU.QuestionSet.dataModels.responses.ResponseOption;
import com.academy.HowRU.QuestionSet.services.QuestionSetService;
import com.academy.HowRU.QuestionSet.services.QuestionViewService;
import com.academy.HowRU.QuestionSet.viewModels.QuestionSetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class QuestionSetController {

    @Autowired
    private QuestionViewService questionViewService;

    @Autowired
    private QuestionSetService questionSetService;

    @GetMapping("/questionsets")
    public ResponseEntity<List<QuestionSetView> > getAllQuestionSets() {
        URI location= URI.create("/questionsets");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);


        return new ResponseEntity<List<QuestionSetView> >(questionViewService.getAllQuestionSetViews(),
                responseHeaders, HttpStatus.OK);

    }

    @GetMapping("/questionsets/{id}")
    public ResponseEntity<QuestionSetView> getQuestionSetById(@PathVariable("id") Long id) {
        URI location= URI.create("/questionsets/" + id.toString());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("QuestionSetId", id.toString());
        return new ResponseEntity<QuestionSetView>(questionViewService.getQuestionSetView(id),
                responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/questionsets/user/{username}")
    public ResponseEntity< List<QuestionSetView> >getQuestionSetsByUser(@PathVariable("username") String username) {
        URI location= URI.create("/questionsets/user/" + username);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("user", username);
        return new ResponseEntity<List<QuestionSetView> >(questionViewService.getQuestionSetViewsByUser(username),
                responseHeaders, HttpStatus.OK);


    }




}
