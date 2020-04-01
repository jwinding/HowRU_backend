package com.academy.HowRU.UserResponse.dataModels;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.user.data.User;
import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@Entity
public class TextResponse extends UserResponse {

    private String text;

    public TextResponse(){
        super(null, null, null, null);
    }

    public TextResponse (Question question, String questionText, User user, LocalDateTime responseTime, String text){
        super(question, questionText, user, responseTime);
        this.text = text;
    }
}