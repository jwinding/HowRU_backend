package com.academy.HowRU.UserResponse.dataModels;
import com.academy.HowRU.QuestionSet.dataModels.Question;
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
    protected Question question;
    @ManyToOne
    protected User user;
    protected String questionText;
    protected LocalDateTime responseTime;

    public UserResponse(Question question, String questionText, User user, LocalDateTime responseTime){
        this.question=question;
        this.questionText = questionText;
        this.user = user;
        this.responseTime = responseTime;
    }


}
