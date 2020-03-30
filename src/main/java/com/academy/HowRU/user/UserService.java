package com.academy.HowRU.user;

import com.academy.HowRU.user.data.User;
import com.academy.HowRU.user.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public boolean registerNewUser(String username, String password, String email){

        if( userRepo.findByUsername(username) == null){

            User u = new User(username, passwordEncoder.encode(password), email);
            userRepo.save(u);
            return true;
        }
        return false;
    }


}
