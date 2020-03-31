package com.academy.HowRU.UserResponse.dataModels;
import com.academy.HowRU.QuestionSet.dataModels.Question;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public abstract class UserResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    protected Question question;

    public UserResponse(Question question){
        this.question=question;
    }


}
