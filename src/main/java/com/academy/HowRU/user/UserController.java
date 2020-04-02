package com.academy.HowRU.user;

import com.academy.HowRU.QuestionSet.viewModels.QuestionSetView;
import com.academy.HowRU.user.data.User;
import com.academy.HowRU.user.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserViewService userViewService;

    @Autowired
    private UserService userService;

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


    @PostMapping("/login")
    public Map<String,String> login(@RequestBody Map<String,String> map ){

        Map<String,String> result = new HashMap<>();

        Boolean loggedIn = userService.login(map.get("username"),map.get("password"));

        result.put("username", map.get("username"));
        result.put("loggedIn", loggedIn.toString());
        result.put("timestamp", LocalDateTime.now().toString());

        return result;

    }

    @PostMapping("/create")
    public void createUser(String username,String password, String email){


        userService.registerNewUser(username,password,email);

    }



}
