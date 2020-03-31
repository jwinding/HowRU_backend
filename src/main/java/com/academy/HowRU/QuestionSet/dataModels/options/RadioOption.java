package com.academy.HowRU.QuestionSet.dataModels.options;

import com.academy.HowRU.QuestionSet.dataModels.Question;
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

    public RadioOption(){ super(null); }
    public RadioOption(Question question){ super(question); }

    public RadioOption(Question question, String option, int value) {
        super(question);
        this.option = option;
        this.value = value;
    }
}
