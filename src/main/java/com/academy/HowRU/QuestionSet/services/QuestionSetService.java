package com.academy.HowRU.QuestionSet.services;

import com.academy.HowRU.QuestionSet.dataModels.*;
import com.academy.HowRU.QuestionSet.dataModels.options.*;
import com.academy.HowRU.QuestionSet.repositories.QuestionRepository;
import com.academy.HowRU.QuestionSet.repositories.QuestionSetRepository;
import com.academy.HowRU.QuestionSet.repositories.ResponseOptionRepository;
import com.academy.HowRU.UserResponse.dataModels.UserResponse;
import com.academy.HowRU.UserResponse.repositories.UserResponseRepository;
import com.academy.HowRU.errorHandling.exceptions.EntityNotFoundException;
import com.academy.HowRU.user.UserService;
import com.academy.HowRU.user.data.User;
import com.academy.HowRU.user.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    private UserResponseRepository userResponseRepository;
    @Autowired
    private UserRepository userRepository;


    public List<QuestionSet> getAllQuestionSets(){
        return (List)questionSetRepository.findAll();
    }

    public List<QuestionSet> getAllQuestionSetsByUser(String username) throws EntityNotFoundException {
        Optional<User> user = userService.findByUsername(username);
        if(user.isEmpty()){
            throw new EntityNotFoundException("No user with username "+username + " exists in the database.");
        } else {
            return questionSetRepository.findAllByCreator(user.get());
        }
    }

    public QuestionSet createNewQuestionSet(String name, String username) throws EntityNotFoundException {
        LocalDateTime now = LocalDateTime.now();
        Optional<User> user = userService.findByUsername(username);
        if(user.isEmpty()){
            throw new EntityNotFoundException("No user with username " + username + "exists in the database.");
        }
        QuestionSet qs = new QuestionSet(name, user.get(), now);
        return questionSetRepository.saveAndFlush(qs);
    }

    public Optional<QuestionSet> getQuestionSet(String name, String username) throws EntityNotFoundException {
        List<QuestionSet> qsList = getAllQuestionSetsByUser(username);
        return qsList.stream().filter( q -> q.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Optional<QuestionSet> getQuestionSet(Long id){
        return questionSetRepository.findById(id);
    }

    public void deleteQuestionSet(Long id){
        questionSetRepository.deleteById(id);
    }

    public Optional<Question> getQuestion(Long id){ return questionRepository.findById(id); }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
    public Question createNewQuestion(String questionSetName, String username, ResponseType responseType, String question)
            throws EntityNotFoundException {
        var qs = getQuestionSet(questionSetName,username);
        if(qs.isPresent()){
            return createNewQuestion(qs.get(),responseType,question);
        } else
            return null;
    }

    public Question createNewQuestion(QuestionSet questionSet, ResponseType responseType, String question){
        Question q = new Question(question, responseType, questionSet);
        return  questionRepository.saveAndFlush(q);
    }




    public SliderOption createNewSliderOption(Long questionId, Integer min, Integer max,
                                                String min_description, String max_description ) throws EntityNotFoundException {
        return (SliderOption)createNewResponse(questionId,null,min,max,min_description,max_description,null,null);
    }

    public CheckboxOption createNewCheckboxOption(Long questionId, Integer value, String option) throws EntityNotFoundException {
        return (CheckboxOption)createNewResponse(questionId,value, null,null,null,null, option, null);
    }
    public RadioOption createNewRadioOption(Long questionId, Integer value, String option) throws EntityNotFoundException {
        return (RadioOption)createNewResponse(questionId,value, null,null,null,null, option, null);
    }
    public TextFieldOption createNewTextFieldOption(Long questionId, String text) throws EntityNotFoundException {
        return (TextFieldOption)createNewResponse(questionId,null, null,null,null,null, null, text);
    }


    public SliderOption createNewSliderOption(Question question, Integer min, Integer max,
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
                                            String option, String text ) throws EntityNotFoundException {
        var question = questionRepository.findById(questionId);
        if(question.isEmpty()){
            throw new EntityNotFoundException("No question with id " + questionId.toString() + " exists in the database.");
        } else {
            return createNewResponse(question.get(), value,min,max,min_description,max_description,option, text);
        }
    }

    public ResponseOption createNewResponse(Question question, Integer value, Integer min, Integer max,
                                            String min_description, String max_description,
                                            String option, String text ) {
        ResponseOption response;

        switch (question.getResponseType()){

            case RANGE:
                response = new SliderOption(question,min,max,min_description,max_description);
                break;
            case RADIO:
                response = new RadioOption(question,option,value);
                break;
            case CHECKBOX:
                response = new CheckboxOption(question, option,value);
                break;
            case TEXT:
                response = new TextFieldOption(question, text);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + question.getResponseType());
        }
        return responseOptionRepository.saveAndFlush(response);
    }

    public HashMap<Long, Boolean> checkQuestionSetsForAnswered(String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        HashMap<Long, Boolean> isAnsweredMap = new HashMap<>();
        if(userOptional.isPresent()){
            User user = userOptional.get();
            List <QuestionSet> allQSByUser = questionSetRepository.findAllByCreator(user);
            for(QuestionSet q : allQSByUser){
                List<Question> questions = questionRepository.findAllByQuestionSet(q);
                for(Question q2 : questions){
                    List<ResponseOption> responseOptions = responseOptionRepository.findByQuestion(q2);
                    for (ResponseOption ro : responseOptions){
                        List<UserResponse> userResponses = userResponseRepository.findByOption(ro);
                        if (userResponses.isEmpty()) {
                            isAnsweredMap.put(q.getId(), false);
                            break;
                        } else {
                            for (UserResponse ur : userResponses){
                                if (ur.getResponseTime().toLocalDate().equals(LocalDateTime.now().toLocalDate())){
                                    isAnsweredMap.put(q.getId(), true);
                                } else {
                                    isAnsweredMap.put(q.getId(), false);
                                }
                            }
                        }
                    }
                }
            }
        }
        return isAnsweredMap;
    }


    public void updateQuestionSet(QuestionSet questionSet) {
        var qs = questionRepository.findById(questionSet.getId());
        if(qs.isPresent()) {
            questionSetRepository.save(questionSet);
        }
    }
}
