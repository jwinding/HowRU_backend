package com.academy.HowRU.QuestionSet.dataModels.options;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public abstract class ResponseOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    protected Question question;

    public ResponseOption(Question question){
        this.question=question;
    }


}
