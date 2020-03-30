package com.academy.HowRU.QuestionSet.dataModels;


import lombok.Data;

import javax.persistence.*;

/***
 * Option to write information not possible to represent in other response types.
 */
@Data
@Entity
public class TextFieldOption extends ResponseOption {

    private String text;

}
