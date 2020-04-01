package com.academy.HowRU.user;

import com.academy.HowRU.QuestionSet.viewModels.QuestionSetView;
import com.academy.HowRU.user.data.User;
import com.academy.HowRU.user.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserViewService userViewService;

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUserList(){
        URI location= URI.create("/user");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return new ResponseEntity<List<User> >(userViewService.getAllUsers(),
                responseHeaders, HttpStatus.OK);

    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username){
        URI location= URI.create("/user/" + username);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("user", username);


        return new ResponseEntity<User>(userViewService.getUsers(username),
                responseHeaders, HttpStatus.OK);

    }

    @PostMapping("/")
    public void createUser(String username,String password, String email){


        userService.registerNewNewUser(username,password,email);

    }


}
