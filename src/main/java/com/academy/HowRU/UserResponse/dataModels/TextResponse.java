package com.academy.HowRU.UserResponse.dataModels;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class TextResponse extends UserResponse {

    private String text;

    public TextResponse(){
        super(null);
    }

    public TextResponse (Question question, String value){
        super(question);
        this.text = value;
    }
}