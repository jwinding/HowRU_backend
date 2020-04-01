package com.academy.HowRU.QuestionSet.inputModels.validators;

import com.academy.HowRU.QuestionSet.inputModels.ResponseOptionInput;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ResponseOptionValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ResponseOptionInput.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors e) {

        if(o instanceof ResponseOptionInput ) {
            ResponseOptionInput rInput = (ResponseOptionInput) o;

            switch (rInput.getType()){

                case RANGE:
                    if(rInput.getMin()==null){
                        e.rejectValue("min", "min.empty");
                    }
                    if(rInput.getMax()==null){
                        e.rejectValue("max", "max.empty");
                    }
                    if(rInput.getMin().intValue() >= rInput.getMax().intValue() ){
                        e.rejectValue("min", "min.invalid_range");
                    }

                    break;
                case RADIO:
                case CHECKBOX:

                    if(rInput.getOption()==null || rInput.getOption()==""){
                        e.rejectValue("option", "option.empty");
                    }
                    if(rInput.getValue()==null){
                        e.rejectValue("value", "value.empty");
                    }
                    break;
                case TEXT:

                    break;
                default:
                    e.rejectValue("type", "type.invalid");
            }

        }

    }
}
