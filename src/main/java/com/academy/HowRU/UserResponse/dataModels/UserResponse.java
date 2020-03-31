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
    protected String username;
    protected String questionText;
    protected LocalDateTime responseTime;

    public UserResponse(Question question, String questionText, String user, LocalDateTime responseTime){
        this.question=question;
        this.questionText = questionText;
        this.username = user;
        this.responseTime = responseTime;
    }


}
