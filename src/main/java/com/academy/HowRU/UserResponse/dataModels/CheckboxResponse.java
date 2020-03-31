package com.academy.HowRU.UserResponse.dataModels;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class CheckboxResponse extends UserResponse {

    private Integer value;

    public CheckboxResponse(){
        super(null);
    }

    public CheckboxResponse (Question question, Integer value){
        super(question);
        this.value = value;
    }

}