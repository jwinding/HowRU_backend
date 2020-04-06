package com.academy.HowRU.UserResponse.services;

import com.academy.HowRU.QuestionSet.dataModels.Question;
import com.academy.HowRU.QuestionSet.dataModels.QuestionSet;
import com.academy.HowRU.QuestionSet.dataModels.options.*;
import com.academy.HowRU.QuestionSet.repositories.QuestionRepository;
import com.academy.HowRU.QuestionSet.repositories.ResponseOptionRepository;
import com.academy.HowRU.QuestionSet.services.QuestionSetService;
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
    @Autowired
    private QuestionSetService questionSetService;


    public UserResponse createUserResponse(Long optionId, String username, Integer value, String text){
        var rec = new UserResponseReceiver(optionId,username,value,text);
        return createUserResponse(rec);
    }

    public UserResponse createUserResponse(Long optionId, String username, Integer value, String text, LocalDateTime time){
        var rec = new UserResponseReceiver(optionId,username,value,text);
        return createUserResponse(rec, time);
    }


    public UserResponse createUserResponse(UserResponseReceiver response, LocalDateTime time){

        ResponseOption responseOption = responseOptionRepository.findById(response.getOptionId()).get();

        Question question = responseOption.getQuestion();
        User user = userService.findByUsername(response.getUsername()).get();

        switch(question.getResponseType()){
            case RADIO:
                return userResponseRepository.save(new RadioResponse((RadioOption) responseOption, question.getQuestion(), user, time,
                        ((RadioOption)responseOption).getValue(),((RadioOption)responseOption).getOption()));
            case CHECKBOX:
                return userResponseRepository.save(new CheckboxResponse((CheckboxOption) responseOption, question.getQuestion(), user, time,
                        ((CheckboxOption)responseOption).getValue(),((CheckboxOption)responseOption).getOption()));
            case RANGE:
                return userResponseRepository.save(new SliderResponse((SliderOption) responseOption, question.getQuestion(), user, time,
                        response.getValue()));
            case TEXT:
                return userResponseRepository.save(new TextResponse((TextFieldOption) responseOption, question.getQuestion(), user, time,
                        response.getText()));
            default:
                return null;
        }
    }


    public UserResponse createUserResponse(UserResponseReceiver response){
        LocalDateTime time = LocalDateTime.now();
        ResponseOption responseOption = responseOptionRepository.findById(response.getOptionId()).get();

        Question question = responseOption.getQuestion();
        User user = userService.findByUsername(response.getUsername()).get();

        switch(question.getResponseType()){
            case RADIO:
                return userResponseRepository.save(new RadioResponse((RadioOption) responseOption, question.getQuestion(), user, time,
                        ((RadioOption)responseOption).getValue(),((RadioOption)responseOption).getOption()));
            case CHECKBOX:
                return userResponseRepository.save(new CheckboxResponse((CheckboxOption) responseOption, question.getQuestion(), user, time,
                        ((CheckboxOption)responseOption).getValue(),((CheckboxOption)responseOption).getOption()));
            case RANGE:
                return userResponseRepository.save(new SliderResponse((SliderOption) responseOption, question.getQuestion(), user, time,
                        response.getValue()));
            case TEXT:
                return userResponseRepository.save(new TextResponse((TextFieldOption) responseOption, question.getQuestion(), user, time,
                        response.getText()));
            default:
                return null;
        }
    }


    public List<UserResponseView> getAllResponses() {

        return makeViewList(userResponseRepository.findAll());
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

        var responsesToQuestion = ((List<UserResponse>)userResponseRepository.findAll())
                .stream()
                .filter(r -> r.getOption().getQuestion().getId() == question.getId())
                .collect(Collectors.toList());

        return makeViewList(responsesToQuestion);
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

        return makeViewList(userResponseRepository.findByUser(user));
    }

    public List<UserResponseView> getResponsesToQuestionSetByUser(Long questionSetId, String username) {
        var user = userService.findByUsername(username);
        var qs = questionSetService.getQuestionSet(questionSetId);

        if(qs.isEmpty() || user.isEmpty()){
            return new ArrayList<>();
        }
        return getResponsesToQuestionSetByUser(qs.get(), user.get());

    }
    public List<UserResponseView> getResponsesToQuestionSetByUser(QuestionSet Qs, User user) {

        var userResponses = userResponseRepository.findByUser(user);
        var userResponsesInQs=userResponses.stream()
                .filter( r -> r.getOption().getQuestion().getQuestionSet().getId() == Qs.getId())
                .collect(Collectors.toList());

        return makeViewList(userResponsesInQs);

    }




    private List<UserResponseView> makeViewList(Iterable<UserResponse> responseList){
        return ((List<UserResponse>)responseList).stream()
                .map(t -> UserResponseView.from(t))
                .collect(Collectors.toList());
    }



}
