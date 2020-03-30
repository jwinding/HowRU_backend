package com.academy.HowRU.QuestionSet.dataModels;

import lombok.Data;

import javax.persistence.*;

/***
 * Option to choose a numerical value in a defined range.
 */
@Data
@Entity
public class SliderOption extends ResponseOption {


    private int min;
    private int max;

    private String min_description;
    private String max_description;


    @Override
    public Question getQuestion() {
        return question;
    }
}
