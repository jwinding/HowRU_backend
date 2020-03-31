package com.academy.HowRU.QuestionSet.services;

import com.academy.HowRU.QuestionSet.dataModels.*;
import com.academy.HowRU.QuestionSet.dataModels.responses.*;
import com.academy.HowRU.QuestionSet.repositories.QuestionRepository;
import com.academy.HowRU.QuestionSet.repositories.QuestionSetRepository;
import com.academy.HowRU.QuestionSet.repositories.ResponseOptionRepository;
import com.academy.HowRU.user.UserService;
import com.academy.HowRU.user.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionSetService {
    @Autowired
    private QuestionSetRepository questionSetRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ResponseOptionRepository responseOptionRepository;


    public List<QuestionSet> getAllQuestionSets(){
        return (List)questionSetRepository.findAll();
    }

    public QuestionSet getAllQuestionSets(Long id){
        return questionSetRepository.findById(id).get();
    }

    public List<QuestionSet> getAllQuestionSetsByUser(String username){
        Optional<User> user = userService.findByUsername(username);
        if(user.isEmpty()){
            return new ArrayList<QuestionSet>();
        } else {
            return questionSetRepository.findAllByCreator(user.get());
        }
    }

    public QuestionSet createNewQuestionSet(String name, String username){
        LocalDateTime now = LocalDateTime.now();
        Optional<User> user = userService.findByUsername(username);
        QuestionSet qs = new QuestionSet(name, user.get(), now);
        return questionSetRepository.save(qs);
    }

    public Optional<QuestionSet> getQuestionSet(String name, String username){
        List<QuestionSet> qsList = getAllQuestionSetsByUser(username);
        return qsList.stream().filter( q -> q.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Optional<QuestionSet> getQuestionSet(Long id){
        return questionSetRepository.findById(id);
    }

    public Question createNewQuestion(String name, String username, ResponseType responseType, String question){
        var qs = getQuestionSet(name,username);
        if(qs.isPresent()){
            return createNewQuestion(qs.get(),responseType,question);
        } else
            return null;
    }

    public Question createNewQuestion(QuestionSet questionSet, ResponseType responseType, String question){
        Question q = new Question(question, responseType, questionSet);
        return  questionRepository.save(q);
    }




    public SliderOption createNewSliderResponse(Long question, Integer min, Integer max,
                                                String min_description, String max_description ){
        return (SliderOption)createNewResponse(question,null,min,max,min_description,max_description,null,null);
    }

    public CheckboxOption createNewCheckboxOption(Long question, Integer value, String option){
        return (CheckboxOption)createNewResponse(question,value, null,null,null,null, option, null);
    }
    public RadioOption createNewRadioOption(Long question, Integer value, String option){
        return (RadioOption)createNewResponse(question,value, null,null,null,null, option, null);
    }
    public TextFieldOption createNewTextFieldOption(Long question, String text){
        return (TextFieldOption)createNewResponse(question,null, null,null,null,null, null, text);
    }


    public SliderOption createNewSliderResponse(Question question, Integer min, Integer max,
                                                String min_description, String max_description ){
        return (SliderOption)createNewResponse(question,null,min,max,min_description,max_description,null,null);
    }

    public CheckboxOption createNewCheckboxOption(Question question, Integer value, String option){
        return (CheckboxOption)createNewResponse(question,value, null,null,null,null, option, null);
    }
    public RadioOption createNewRadioOption(Question question, Integer value, String option){
        return (RadioOption)createNewResponse(question,value, null,null,null,null, option, null);
    }
    public TextFieldOption createNewTextFieldOption(Question question, String text){
        return (TextFieldOption)createNewResponse(question,null, null,null,null,null, null, text);
    }

    public ResponseOption createNewResponse(Long questionId, Integer value, Integer min, Integer max,
                                            String min_description, String max_description,
                                            String option, String text ){
        var question = questionRepository.findById(questionId);
        if(question.isEmpty()){
            throw new EntityNotFoundException();
        } else {
            return createNewResponse(question.get(), value,min,max,min_description,max_description,option, text);
        }
    }

    public ResponseOption createNewResponse(Question question, Integer value, Integer min, Integer max,
                                            String min_description, String max_description,
                                            String option, String text ) {
        ResponseOption response;

        switch (question.getResponseType()){

            case SLIDER:
                response = new SliderOption(question,min,max,min_description,max_description);
                break;
            case RADIO:
                response = new RadioOption(question,option,value);
                break;
            case CHECKBOX:
                response = new CheckboxOption(question, option,value);
                break;
            case TEXTFIELD:
                response = new TextFieldOption(question, text);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + question.getResponseType());
        }
        return responseOptionRepository.save(response);
    }

}
