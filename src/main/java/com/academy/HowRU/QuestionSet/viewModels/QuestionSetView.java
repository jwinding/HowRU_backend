package com.academy.HowRU.QuestionSet.viewModels;

import com.academy.HowRU.QuestionSet.dataModels.QuestionSet;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuestionSetView {

    private Long id;

    private String name;
    private String creator;
    private LocalDateTime created;

    private List<QuestionView> questions;

    private QuestionSetView(){

    }

    public static QuestionSetView from(QuestionSet qs){

        QuestionSetView qsv = new QuestionSetView();
        qsv.setId(qs.getId());
        qsv.setName(qs.getName());
        qsv.setCreator(qs.getCreator().getUsername());
        qsv.setCreated(qs.getCreated());
        qsv.setQuestions(
                qs.getQuestions().stream()
                    .map(q -> QuestionView.from(q))
                    .collect(Collectors.toList())
        );
        return qsv;

    }

}
