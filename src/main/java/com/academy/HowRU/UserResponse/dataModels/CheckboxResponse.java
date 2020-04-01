package com.academy.HowRU.UserResponse.dataModels;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.user.data.User;
import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@Entity
public class CheckboxResponse extends UserResponse {

    private Integer value;
    private String option;

    public CheckboxResponse(){
        super(null, null, null, null);
    }

    public CheckboxResponse (Question question, String questionText, User user, LocalDateTime responseTime, Integer value, String option){
        super(question, questionText, user, responseTime);
        this.value = value;
        this.option=option;
    }

}