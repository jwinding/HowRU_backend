package com.academy.HowRU.UserResponse.dataModels;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.user.data.User;
import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@Entity
public class SliderResponse extends UserResponse {

    private Integer value;

    public SliderResponse(){
        super(null, null, null, null);
    }

    public SliderResponse (Question question, String questionText, User user, LocalDateTime responseTime, Integer value){
        super(question, questionText,user, responseTime);
        this.value = value;
    }

}
