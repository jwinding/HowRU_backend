package com.academy.HowRU.UserResponse.dataModels;
import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.QuestionSet.dataModels.options.ResponseOption;
import com.academy.HowRU.user.data.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public abstract class UserResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    protected ResponseOption option;

    @ManyToOne
    protected User user;
    protected String questionText;
    protected LocalDateTime responseTime;

    public UserResponse(ResponseOption option, String questionText, User user, LocalDateTime responseTime){
        this.option = option;
        this.questionText = questionText;
        this.user = user;
        this.responseTime = responseTime;
    }


}
