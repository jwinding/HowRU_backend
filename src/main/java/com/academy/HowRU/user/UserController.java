package com.academy.HowRU.user;

import com.academy.HowRU.errorHandling.exceptions.EntityNotFoundException;
import com.academy.HowRU.user.data.User;
import com.academy.HowRU.user.data.UserInputModel;
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

@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:80",
        "http://ec2-13-53-42-207.eu-north-1.compute.amazonaws.com:80"})
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
    public ResponseEntity<User> getUser(@PathVariable("username") String username) throws EntityNotFoundException {
        URI location= URI.create("/user/" + username);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("user", username);


        return new ResponseEntity<User>(userViewService.getUser(username),
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

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody UserInputModel userData){
        URI location= URI.create("/user/");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("user", userData.getUsername());
        var user = userService.registerNewUser(userData.getUsername(),userData.getPassword(),userData.getEmail());
        return new ResponseEntity<User>(user,
                responseHeaders, HttpStatus.CREATED);

    }



}
