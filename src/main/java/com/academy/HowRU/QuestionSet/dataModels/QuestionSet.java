package com.academy.HowRU.QuestionSet.dataModels;

import com.academy.HowRU.user.data.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class QuestionSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    private int version;

    private User creator;
    private LocalDateTime created;

    @OneToMany(mappedBy = "questionSet")
    private List<Question> questions;



}
