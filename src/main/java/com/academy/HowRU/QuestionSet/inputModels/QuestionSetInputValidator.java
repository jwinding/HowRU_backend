package com.academy.HowRU.QuestionSet.inputModels;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class QuestionSetInputValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return QuestionSetInput.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors e) {

        if(o instanceof QuestionSetInput ) {
            QuestionSetInput qsInput = (QuestionSetInput) o;
            if(qsInput.getName()==null || qsInput.getName()=="" ){
                e.rejectValue("name", "name.empty");
            }
            if(qsInput.getCreator()==null || qsInput.getCreator()=="" ){
                e.rejectValue("creator", "creator.empty");
            }
            if(qsInput.getQuestions() == null || qsInput.getQuestions().size() == 0){
                e.rejectValue("questions", "questions.empty");
            }

            QuestionInputValidator questionInputValidator = new QuestionInputValidator();
            for(QuestionInput qInput:qsInput.getQuestions()){
                questionInputValidator.validate(qInput,e);
            }

        }

    }
}
