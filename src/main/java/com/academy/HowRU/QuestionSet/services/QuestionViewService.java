package com.academy.HowRU.QuestionSet.services;

import com.academy.HowRU.QuestionSet.viewModels.QuestionSetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionViewService {

    @Autowired
    private QuestionSetService questionSetService;


    public List<QuestionSetView> getAllQuestionSetViews(){

        var qsList = questionSetService.getAllQuestionSets();

        return qsList.stream().map(qs-> QuestionSetView.from(qs))
                    .collect(Collectors.toList());

    }




}
