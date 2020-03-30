package com.academy.HowRU.QuestionSet.dataModels.responses;


import com.academy.HowRU.QuestionSet.dataModels.Question;
import lombok.Data;
import org.hibernate.engine.internal.CacheHelper;

import javax.persistence.*;

/***
 * Option to choose one value out of some finite set of possible options.
 */
@Data
@Entity
public class CheckboxOption extends ResponseOption {

    private String option;
    private int value;

    public CheckboxOption() {
        super(null);
    }

    public CheckboxOption(Question question){
        super(question);
    }

    public CheckboxOption(Question question, String option, int value) {
        super(question);
        this.option = option;
        this.value = value;
    }
}
