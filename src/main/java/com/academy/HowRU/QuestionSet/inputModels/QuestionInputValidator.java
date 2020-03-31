package com.academy.HowRU.QuestionSet.inputModels;

import com.academy.HowRU.QuestionSet.dataModels.responses.ResponseType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.stream.Stream;

public class QuestionInputValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return QuestionInput.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors e) {

        if(o instanceof QuestionInput ) {
            QuestionInput qInput = (QuestionInput) o;
            if (qInput.getQuestion() == null || qInput.getQuestion() == "") {
                e.rejectValue("question", "question.empty");
            }
            if (qInput.getResponses() == null || qInput.getResponses().size() == 0) {
                e.rejectValue("responses", "responses.empty");
            }
            if ((qInput.getType() == ResponseType.RANGE || qInput.getType() == ResponseType.TEXT)
                    && qInput.getResponses().size() > 1) {
                e.rejectValue("respones", "responses.too_many_for_type");
            }


            ResponseOptionValidator responseOptionValidator = new ResponseOptionValidator();
            for(ResponseOptionInput rInput: qInput.getResponses()){
                responseOptionValidator.validate(rInput, e);
            }
        }

    }
}
