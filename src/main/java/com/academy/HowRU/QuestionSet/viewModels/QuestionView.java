package com.academy.HowRU.QuestionSet.viewModels;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.QuestionSet.dataModels.ResponseType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuestionView {

    private Long id;

    private String question;
    private ResponseType type;

    private List<ResponseOptionView> responses;

    private QuestionView(){
        responses = new ArrayList<>();
    }

    public static QuestionView from(Question question){

        QuestionView qv = new QuestionView();
        qv.setId(question.getId());
        qv.setQuestion(question.getQuestion());
        qv.setType(question.getResponseType());

        qv.setResponses(
                question.getOptions().stream()
                        .map(t -> ResponseOptionView.from(t))
                        .collect(Collectors.toList())
        );

        return qv;
    }


}
