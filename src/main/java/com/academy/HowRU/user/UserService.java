package com.academy.HowRU.user;

import com.academy.HowRU.user.data.User;
import com.academy.HowRU.user.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public boolean registerNewUser(String username, String password, String email){

        if(userRepo.findByUsername(username).isEmpty()){
            User u = new User(username, passwordEncoder.encode(password), email,LocalDateTime.now());
            userRepo.save(u);
            return true;
        }
        return false;
    }

    public boolean login(String username, String password){

       Optional<User> user = userRepo.findByUsername(username);
       if(user.isEmpty()){
           return false;
       } else {
           if(passwordEncoder.encode(password).equals(user.get().getPassword())){
               return true;
           }
       }

        return false;
    }

    public void registerNewNewUser(String username, String password, String email){

        User user = new User();

        if(userRepo.findByUsername(username).isEmpty()){
            User u = new User(username, passwordEncoder.encode(password), email,LocalDateTime.now());
            userRepo.save(u);
            
        }
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setCreatedAt(LocalDateTime.now());

        userRepo.save(user);

    }


}
