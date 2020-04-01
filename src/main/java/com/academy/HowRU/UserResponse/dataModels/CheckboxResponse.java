package com.academy.HowRU.UserResponse.dataModels;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.QuestionSet.dataModels.options.CheckboxOption;
import com.academy.HowRU.user.data.User;
import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@Entity
public class CheckboxResponse extends UserResponse {

    private Integer value;
    private String optionText;

    public CheckboxResponse(){
        super(null, null, null, null);
    }

    public CheckboxResponse (CheckboxOption option, String questionText, User user, LocalDateTime responseTime, Integer value, String optionText){
        super(option, questionText, user, responseTime);
        this.value = value;
        this.optionText = optionText;
    }

}