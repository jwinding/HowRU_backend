package com.academy.HowRU.UserResponse.controllers;

import com.academy.HowRU.QuestionSet.controllers.InputController;
import com.academy.HowRU.QuestionSet.inputModels.validators.ResponseOptionValidator;
import com.academy.HowRU.UserResponse.inputModels.UserResponseReceiver;
import com.academy.HowRU.UserResponse.services.UserResponseService;
import com.academy.HowRU.UserResponse.viewModels.UserResponseView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserResponseController {

    @Autowired
    private UserResponseService userResponseService;

    private ResponseOptionValidator validator;
    private final Logger log = LoggerFactory.getLogger(UserResponseController.class);

    public UserResponseController(){
        validator = new ResponseOptionValidator();
    }


    @PostMapping(value="/response", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> registerNewResponse(@RequestBody UserResponseReceiver response, BindingResult result){
        URI location= URI.create("/response");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        Map<String, String> res = new HashMap<>();
        res.put("user", response.getUsername());

       validator.validate(response, result);
        if(result.hasErrors()){
            log.error("User response validation error", result);
            res.put("BadRequest", "Input not in correct format!");

            for(var error:result.getAllErrors()){
                res.put(error.getObjectName(), error.toString());
            }

            return new ResponseEntity<Map<String, String>>(res,
                    responseHeaders, HttpStatus.BAD_REQUEST);
        } else {
            userResponseService.createUserResponse(response);

            res.put("responseId", response.getOptionId().toString());
            res.put("value", response.getValue() != null ? response.getValue().toString() : "null");
            res.put("text", response.getText() != null ? response.getText() : "");

            return new ResponseEntity<>(res, responseHeaders, HttpStatus.CREATED);
        }
    }


    @GetMapping("/response")
    public ResponseEntity<List<UserResponseView>> getAllResponses(){
        URI location= URI.create("/response");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return new ResponseEntity<>( userResponseService.getAllResponses(), responseHeaders, HttpStatus.OK );
    }


    @GetMapping("/response/{user}")
    public ResponseEntity<List<UserResponseView>> getAllResponsesFromUser(@PathVariable("user") String user){
        URI location= URI.create("/response/"+user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("user", user);

        return new ResponseEntity<>(userResponseService.getAllResponsesByUser(user),responseHeaders,HttpStatus.OK);
    }


    @GetMapping("/response/question/{questionId}")
    public ResponseEntity<List<UserResponseView>> getAllResponses(@PathVariable("questionId") Long questionId){
        URI location= URI.create("/response/question/"+questionId.toString());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("questionId", questionId.toString());


        return new ResponseEntity<>( userResponseService.getAllResponsesToQuestion(questionId), responseHeaders, HttpStatus.OK);
    }
}
