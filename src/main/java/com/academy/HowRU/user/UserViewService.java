package com.academy.HowRU.user;


import com.academy.HowRU.QuestionSet.dataModels.QuestionSet;
import com.academy.HowRU.user.data.User;
import com.academy.HowRU.user.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserViewService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        List<User> userList = (List<User>)userRepository.findAll();
        return userList;
    }

    public User getUsers(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            return new User();
        } else {
            return user.get();
        }
    }


}
