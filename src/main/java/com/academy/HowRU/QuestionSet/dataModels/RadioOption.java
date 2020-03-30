package com.academy.HowRU.QuestionSet.dataModels;

import lombok.Data;

import javax.persistence.*;


/***
 * Option to choose one value out of some finite set of possible options.
 */
@Data
@Entity
public class RadioOption extends ResponseOption {

    private String option;
    private int value;


}