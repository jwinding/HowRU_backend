package com.academy.HowRU.UserResponse.viewModels;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.QuestionSet.dataModels.options.*;
import com.academy.HowRU.QuestionSet.viewModels.ResponseOptionView;
import com.academy.HowRU.UserResponse.dataModels.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseView {

    protected Long id;

    protected String username;
    protected String questionText;
    protected LocalDateTime responseTime;
    protected ResponseType type;
    protected Long responseId;
    protected Long questionId;

    protected int value;

    protected String text;

    //Radio and checkbox
    private String optionText;


    private UserResponseView(){
    }

    public static UserResponseView from(UserResponse response){

        UserResponseView view = new UserResponseView();
        view.setId(response.getId());
        view.setResponseTime(response.getResponseTime());
        view.setResponseId(response.getId());
        view.setQuestionId(response.getOption().getQuestion().getId());
        view.setQuestionText(response.getQuestionText());
        view.setUsername(response.getUser().getUsername());

        if(response instanceof SliderResponse){
            view.setValue(((SliderResponse) response).getValue());
            view.setType(ResponseType.RANGE);
        } else if(response instanceof RadioResponse ){
            view.setValue(((RadioResponse) response).getValue());
            view.setOptionText(((RadioResponse)response).getOptionText());
            view.setType(ResponseType.RADIO);

        } else if(response instanceof CheckboxResponse ){
            view.setValue(((CheckboxResponse) response).getValue());
            view.setOptionText(((CheckboxResponse)response).getOptionText());
            view.setType(ResponseType.CHECKBOX);

        }else if(response instanceof TextResponse){
            view.setText(((TextResponse) response).getText());
            view.setType(ResponseType.TEXT);

        }
        return view;
    }
}
