package com.academy.HowRU.UserResponse.dataModels;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class RadioResponse extends UserResponse {

    private Integer value;

    public RadioResponse(){
        super(null);
    }

    public RadioResponse (Question question, Integer value){
        super(question);
        this.value = value;
    }

}