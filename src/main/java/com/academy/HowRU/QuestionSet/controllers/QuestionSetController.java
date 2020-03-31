package com.academy.HowRU.QuestionSet.controllers;

import com.academy.HowRU.QuestionSet.dataModels.QuestionSet;
import com.academy.HowRU.QuestionSet.services.QuestionSetService;
import com.academy.HowRU.QuestionSet.services.QuestionViewService;
import com.academy.HowRU.QuestionSet.viewModels.QuestionSetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionSetController {

    @Autowired
    private QuestionViewService questionViewService;

    @Autowired
    private QuestionSetService questionSetService;

    @GetMapping("/questionsets")
    public List<QuestionSetView> getAllQuestionSets() {
        return questionViewService.getAllQuestionSetViews();
    }

    @GetMapping("/questionsets/{id}")
    public QuestionSetView getQuestionSetById(@PathVariable("id") Long id) {
        return questionViewService.getQuestionSetView(id);
    }

    @GetMapping("/questionsets/user/{username}")
    public List<QuestionSetView> getQuestionSetsByUser(@PathVariable("username") String username) {
        return questionViewService.getQuestionSetViewsByUser(username);
    }




}
