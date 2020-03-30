package com.academy.HowRU.QuestionSet.services;

import com.academy.HowRU.QuestionSet.dataModels.QuestionSet;
import com.academy.HowRU.QuestionSet.repositories.QuestionSetRepository;
import com.academy.HowRU.user.UserService;
import com.academy.HowRU.user.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionSetService {
    @Autowired
    QuestionSetRepository questionSetRepository;
    @Autowired
    UserService userService;


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
}
