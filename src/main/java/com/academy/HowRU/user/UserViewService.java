package com.academy.HowRU.user;


import com.academy.HowRU.QuestionSet.dataModels.QuestionSet;
import com.academy.HowRU.errorHandling.exceptions.EntityNotFoundException;
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

    public User getUser(String username) throws EntityNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty())
            throw new EntityNotFoundException("No user with username " + username + " exists in the database.");
        else {
            return user.get();
        }
    }


}
