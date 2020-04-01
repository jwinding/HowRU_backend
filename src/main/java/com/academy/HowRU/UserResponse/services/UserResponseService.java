package com.academy.HowRU.UserResponse.services;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.QuestionSet.dataModels.options.CheckboxOption;
import com.academy.HowRU.QuestionSet.dataModels.options.RadioOption;
import com.academy.HowRU.QuestionSet.dataModels.options.ResponseOption;
import com.academy.HowRU.QuestionSet.repositories.QuestionRepository;
import com.academy.HowRU.QuestionSet.repositories.ResponseOptionRepository;
import com.academy.HowRU.UserResponse.dataModels.*;
import com.academy.HowRU.UserResponse.inputModels.UserResponseReceiver;
import com.academy.HowRU.UserResponse.repositories.UserResponseRepository;
import com.academy.HowRU.UserResponse.viewModels.UserResponseView;
import com.academy.HowRU.user.UserService;
import com.academy.HowRU.user.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserResponseService {
    @Autowired
    private UserResponseRepository userResponseRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ResponseOptionRepository responseOptionRepository;
    @Autowired
    private UserService userService;

    public UserResponse createUserResponse(Long optionId, String username, Integer value, String text){
        var rec = new UserResponseReceiver(optionId,username,value,text);
        return createUserResponse(rec);
    }

    public UserResponse createUserResponse(UserResponseReceiver response){
        LocalDateTime time = LocalDateTime.now();
        ResponseOption responseOption = responseOptionRepository.findById(response.getOptionId()).get();

        Question question = responseOption.getQuestion();
        User user = userService.findByUsername(response.getUsername()).get();

        switch(question.getResponseType()){
            case RADIO:
                return userResponseRepository.save(new RadioResponse(question, question.getQuestion(), user, time,
                        response.getValue(),((RadioOption)responseOption).getOption()));
            case CHECKBOX:
                return userResponseRepository.save(new CheckboxResponse(question, question.getQuestion(), user, time,
                        response.getValue(),((CheckboxOption)responseOption).getOption()));
            case RANGE:
                return userResponseRepository.save(new SliderResponse(question, question.getQuestion(), user, time,
                        response.getValue()));
            case TEXT:
                return userResponseRepository.save(new TextResponse(question, question.getQuestion(), user, time,
                        response.getText()));
            default:
                return null;
        }
    }


    public List<UserResponseView> getAllResponses() {

        return ((List<UserResponse>)userResponseRepository.findAll()).stream()
                .map(t -> UserResponseView.from(t))
                .collect(Collectors.toList());
    }

    public List<UserResponseView> getAllResponsesToQuestion(Long questionId) {
        var question = questionRepository.findById(questionId);
        if(question.isPresent()){
            return getAllResponsesToQuestion(question.get());
        } else {
            return new ArrayList<>();
        }

    }

    public List<UserResponseView> getAllResponsesToQuestion(Question question) {
        return userResponseRepository.findByQuestion(question).stream()
                .map(t -> UserResponseView.from(t))
                .collect(Collectors.toList());
    }

    public List<UserResponseView> getAllResponsesByUser(String username) {

        var user = userService.findByUsername(username);
        if(user.isPresent()){
            return getAllResponsesByUser(user.get());
        } else {
            return new ArrayList<>();
        }
    }

    public List<UserResponseView> getAllResponsesByUser(User user) {

        return ((List<UserResponse>)userResponseRepository.findByUser(user)).stream()
                .map(t -> UserResponseView.from(t))
                .collect(Collectors.toList());
    }
}
