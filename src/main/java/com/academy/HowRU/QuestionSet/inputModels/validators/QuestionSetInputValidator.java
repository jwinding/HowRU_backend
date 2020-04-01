package com.academy.HowRU.QuestionSet.inputModels.validators;

import com.academy.HowRU.QuestionSet.inputModels.QuestionInput;
import com.academy.HowRU.QuestionSet.inputModels.QuestionSetInput;
import org.springframework.validation.*;

public class QuestionSetInputValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return QuestionSetInput.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors e) {

        if(o instanceof QuestionSetInput ) {
            QuestionSetInput qsInput = (QuestionSetInput) o;

            ValidationUtils.rejectIfEmpty(e,"name","name.empty");
            ValidationUtils.rejectIfEmpty(e,"creator","name.empty");

//            if(qsInput.getName()==null || qsInput.getName()=="" ){
//                e.rejectValue("name", "name.empty");
//            }
//            if(qsInput.getCreator()==null || qsInput.getCreator()=="" ){
//                e.rejectValue("creator", "creator.empty");
//            }
            if(qsInput.getQuestions() == null || qsInput.getQuestions().size() == 0){
                e.rejectValue("questions", "questions.empty");
            }

            QuestionInputValidator questionInputValidator = new QuestionInputValidator();
           try {
               int index=0;
               for (QuestionInput qInput : qsInput.getQuestions()) {
                   e.pushNestedPath("questions["+index+"]");
                   ValidationUtils.invokeValidator(questionInputValidator, qInput, e);
                    e.popNestedPath();
                    index++;
               }
           } finally {

           }
        }

    }
}
