package com.academy.HowRU.QuestionSet.dataModels.options;

import com.academy.HowRU.QuestionSet.dataModels.Question;
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

    public SliderOption(){ super(null); }

    public SliderOption(Question question, int min, int max, String min_description, String max_description) {
        super(question);
        this.min = min;
        this.max = max;
        this.min_description = min_description;
        this.max_description = max_description;
    }
}
