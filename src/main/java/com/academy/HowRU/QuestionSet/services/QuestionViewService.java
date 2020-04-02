package com.academy.HowRU.QuestionSet.services;

import com.academy.HowRU.QuestionSet.viewModels.QuestionSetView;
import com.academy.HowRU.errorHandling.exceptions.EntityNotFoundException;
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

    public List<QuestionSetView> getQuestionSetViewsByUser(String username)
            throws EntityNotFoundException {
        var qsList = questionSetService.getAllQuestionSetsByUser(username);
        return qsList.stream().map(qs-> QuestionSetView.from(qs))
                .collect(Collectors.toList());
    }

    public QuestionSetView getQuestionSetView(Long id) throws EntityNotFoundException {

        var qs = questionSetService.getQuestionSet(id);
        if(qs.isPresent())
            return QuestionSetView.from(qs.get());
        else
            throw new EntityNotFoundException("No question set with id: "+id.toString() + " exists in the database.");

    }





}
