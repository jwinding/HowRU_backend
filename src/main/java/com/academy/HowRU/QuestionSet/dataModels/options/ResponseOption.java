package com.academy.HowRU.QuestionSet.dataModels.options;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.UserResponse.dataModels.UserResponse;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public abstract class ResponseOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    protected Question question;

    @OneToMany(mappedBy = "option", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    protected List<UserResponse> userResponses;

    public ResponseOption(Question question){
        this.question=question;
    }


}
