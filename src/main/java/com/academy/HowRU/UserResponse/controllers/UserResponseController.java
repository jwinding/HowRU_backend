package com.academy.HowRU.UserResponse.controllers;

import com.academy.HowRU.UserResponse.dataModels.UserResponseReceiver;
import com.academy.HowRU.UserResponse.services.UserResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserResponseController {

    @Autowired
    UserResponseService userResponseService;

    @PostMapping(value="/response", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void registerNewRadioResponse(@RequestBody UserResponseReceiver response){
        userResponseService.createUserResponse(response);
    }
}
