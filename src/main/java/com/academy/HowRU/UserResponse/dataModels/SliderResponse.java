package com.academy.HowRU.UserResponse.dataModels;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class SliderResponse extends UserResponse {

    private Integer value;

    public SliderResponse(){
        super(null);
    }

    public SliderResponse (Question question, Integer value){
        super(question);
        this.value = value;
    }

}
