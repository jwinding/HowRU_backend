package com.academy.HowRU.UserResponse.services;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.QuestionSet.repositories.QuestionRepository;
import com.academy.HowRU.UserResponse.dataModels.*;
import com.academy.HowRU.UserResponse.repositories.UserResponseRepository;
import com.academy.HowRU.user.data.User;
import com.academy.HowRU.user.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserResponseService {
    @Autowired
    UserResponseRepository userResponseRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;

    public UserResponse createUserResponse(UserResponseReceiver response){
        LocalDateTime time = LocalDateTime.now();
        response.setResponseTime(time);
        Question question = questionRepository.findById(response.getQuestionId()).get();
        //User user = userRepository.findByUsername(response.getUsername()).get();
        switch(response.getOptionType()){
            case "radio":
                return userResponseRepository.save(new RadioResponse(question, response.getQuestion(), response.getUsername(), time, response.getValue()));
            case "checkbox":
                return userResponseRepository.save(new CheckboxResponse(question, response.getQuestion(), response.getUsername(), time, response.getValue()));
            case "range":
                return userResponseRepository.save(new SliderResponse(question, response.getQuestion(), response.getUsername(), time, response.getValue()));
            case "text":
                return userResponseRepository.save(new TextResponse(question, response.getQuestion(), response.getUsername(), time, response.getText()));
            default:
                return null;
        }
    }


}
