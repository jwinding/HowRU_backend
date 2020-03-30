package com.academy.HowRU.QuestionSet.dataModels;


import com.academy.HowRU.QuestionSet.dataModels.responses.ResponseOption;
import com.academy.HowRU.QuestionSet.dataModels.responses.ResponseType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private ResponseType responseType;

    @ManyToOne
    private QuestionSet questionSet;

    @OneToMany(mappedBy = "question")
    private List<ResponseOption> options;

    public Question() {
    }

    public Question(String question, ResponseType responseType, QuestionSet questionSet) {
        this.question = question;
        this.responseType = responseType;
        this.questionSet = questionSet;
    }

    public Question(String question, ResponseType responseType, QuestionSet questionSet, List<ResponseOption> options) {
        this.question = question;
        this.responseType = responseType;
        this.questionSet = questionSet;
        this.options = options;
    }
}
