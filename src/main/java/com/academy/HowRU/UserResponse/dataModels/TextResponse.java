package com.academy.HowRU.UserResponse.dataModels;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.QuestionSet.dataModels.options.TextFieldOption;
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

    public TextResponse (TextFieldOption option, String questionText, User user, LocalDateTime responseTime, String text){
        super(option, questionText, user, responseTime);
        this.text = text;
    }
}