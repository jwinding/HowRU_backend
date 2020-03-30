package com.academy.HowRU.QuestionSet.dataModels;

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


}
