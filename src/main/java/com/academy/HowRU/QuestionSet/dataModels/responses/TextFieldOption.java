package com.academy.HowRU.QuestionSet.dataModels.responses;


import com.academy.HowRU.QuestionSet.dataModels.Question;
import lombok.Data;

import javax.persistence.*;

/***
 * Option to write information not possible to represent in other response types.
 */
@Data
@Entity
public class TextFieldOption extends ResponseOption {

    private String text;

    public TextFieldOption() {
        super(null);

    }
    public TextFieldOption(Question question){
        super(question);
    }

    public TextFieldOption(Question question, String text) {
        super(question);
        this.text = text;
    }
}
