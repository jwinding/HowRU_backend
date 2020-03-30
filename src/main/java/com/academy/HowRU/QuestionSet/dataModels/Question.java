package com.academy.HowRU.QuestionSet.dataModels;


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


}
