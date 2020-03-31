package com.academy.HowRU.QuestionSet.services;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.QuestionSet.dataModels.QuestionSet;
import com.academy.HowRU.QuestionSet.dataModels.options.ResponseOption;
import com.academy.HowRU.QuestionSet.inputModels.QuestionInput;
import com.academy.HowRU.QuestionSet.inputModels.QuestionSetInput;
import com.academy.HowRU.QuestionSet.inputModels.ResponseOptionInput;
import com.academy.HowRU.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionSetInputService {

    @Autowired
    private QuestionSetService questionSetService;

    @Autowired
    private UserService userService;

    public QuestionSet saveNewQuestionSet(QuestionSetInput qsInput){

        var qs = questionSetService.createNewQuestionSet(qsInput.getName(), qsInput.getCreator());

        for(QuestionInput qInput: qsInput.getQuestions()){
            var question = saveNewQuestion(qInput, qs);
            for(ResponseOptionInput rInput: qInput.getResponses()){
                saveNewResponseOption(rInput, question);
            }
        }
        return questionSetService.getQuestionSet(qs.getId()).get();

    }

    public Question saveNewQuestion(QuestionInput qInput, QuestionSet questionSet){
        return questionSetService.createNewQuestion(questionSet, qInput.getType(), qInput.getQuestion());
    }

    public ResponseOption saveNewResponseOption(ResponseOptionInput rInput, Question question){

        switch (question.getResponseType()){
            case RANGE:
                return questionSetService.createNewSliderOption(question,rInput.getMin(),rInput.getMax(),
                        rInput.getMin_description(),rInput.getMax_description());

            case RADIO:
                return questionSetService.createNewRadioOption(question, rInput.getValue(),rInput.getOption());
            case CHECKBOX:
                return questionSetService.createNewCheckboxOption(question, rInput.getValue(),rInput.getOption());
            case TEXT:
                return questionSetService.createNewTextFieldOption(question, rInput.getText());
            default:
                throw new IllegalStateException("Unexpected value: " + question.getResponseType());
        }



    }

}
