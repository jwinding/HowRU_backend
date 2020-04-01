package com.academy.HowRU.UserResponse.inputModels;

import com.academy.HowRU.QuestionSet.dataModels.options.SliderOption;
import com.academy.HowRU.QuestionSet.inputModels.ResponseOptionInput;
import com.academy.HowRU.QuestionSet.repositories.QuestionRepository;
import com.academy.HowRU.QuestionSet.repositories.ResponseOptionRepository;
import com.academy.HowRU.QuestionSet.services.QuestionSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserResponseValidator implements Validator {

    @Autowired
    private ResponseOptionRepository responseOptionRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserResponseReceiver.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors e) {

        if(o instanceof UserResponseReceiver ) {
            UserResponseReceiver rInput = (UserResponseReceiver) o;

            if(rInput.getOptionId()==null){
                e.rejectValue("optionId", "optionId.empty");
                return;
            }

            var option = responseOptionRepository.findById(rInput.getOptionId());
            if(option.isEmpty()){
                e.rejectValue("optionId", "optionId.invalid");
                return;
            }

            switch (option.get().getQuestion().getResponseType()){

                case RANGE:
                    if(rInput.getValue()==null){
                        e.rejectValue("value", "value.empty");
                    }
                    if(rInput.getValue() > ((SliderOption)option.get()).getMax()){
                        e.rejectValue("value","value.outsideMax");
                    }
                    if(rInput.getValue() < ((SliderOption)option.get()).getMin()){
                        e.rejectValue("value","value.outsideMin");
                    }

                    break;
                case RADIO:
                case CHECKBOX:

                    break;
                case TEXT:
                    if(rInput.getText() ==null || rInput.getText().strip()==""){
                        e.rejectValue("text","text.empty");
                    }

                    break;
                default:
                    e.rejectValue("type", "type.invalid");
            }

        }

    }


}
