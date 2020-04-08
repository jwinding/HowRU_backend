package com.academy.HowRU.QuestionSet.controllers;


import com.academy.HowRU.QuestionSet.services.QuestionSetService;
import com.academy.HowRU.QuestionSet.services.QuestionViewService;
import com.academy.HowRU.QuestionSet.viewModels.QuestionSetView;
import com.academy.HowRU.errorHandling.exceptions.EntityNotFoundException;
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
@CrossOrigin(origins="*", allowedHeaders="*")
//
//@CrossOrigin(origins = {"http://localhost:3000","http://localhost:80",
//        "http://ec2-13-53-42-207.eu-north-1.compute.amazonaws.com:80"})
public class QuestionSetController {

    @Autowired
    private QuestionViewService questionViewService;

    @Autowired
    private QuestionSetService questionSetService;

    @GetMapping("/questionset")
    public ResponseEntity<List<QuestionSetView> > getAllQuestionSets() {
        URI location= URI.create("/questionset");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return new ResponseEntity<List<QuestionSetView> >(questionViewService.getAllQuestionSetViews(),
                responseHeaders, HttpStatus.OK);

    }

    @GetMapping("/questionset/{id}")
    public ResponseEntity<QuestionSetView> getQuestionSetById(@PathVariable("id") Long id)
            throws EntityNotFoundException {
        URI location= URI.create("/questionset/" + id.toString());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("QuestionSetId", id.toString());
        return new ResponseEntity<QuestionSetView>(questionViewService.getQuestionSetView(id),
                responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/questionset/{id}")
    public ResponseEntity<Map<String,String>> deleteQuestionSetById(@PathVariable("id") Long id) {
        URI location= URI.create("/questionset/" + id.toString());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("QuestionSetId", id.toString());
        Map<String,String> res = new HashMap<>();
        if(questionSetService.getQuestionSet(id).isPresent()){
            questionSetService.deleteQuestionSet(id);
            res.put("timestamp", LocalDateTime.now().toString());
            res.put("deletedQuestionSet", id.toString());
            return new ResponseEntity<>(res,responseHeaders,HttpStatus.ACCEPTED);
        } else {
            res.put("timestamp", LocalDateTime.now().toString());
            res.put("Failed", "No questionSet with id " + id.toString() + "found in database, did you already delete it?");
            return new ResponseEntity<>(res,responseHeaders,HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/question/{questionId}")
    public ResponseEntity<Map<String,String>> deleteQuestionById(@PathVariable("questionId") Long questionId) {
        URI location= URI.create("/question/" + questionId.toString());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("QuestionId", questionId.toString());

        Map<String,String> res = new HashMap<>();

        if(questionSetService.getQuestion(questionId).isPresent()){
            questionSetService.deleteQuestion(questionId);
            res.put("timestamp", LocalDateTime.now().toString());
            res.put("deletedQuestion", questionId.toString());
            return new ResponseEntity<>(res,responseHeaders,HttpStatus.ACCEPTED);
        } else {
            res.put("timestamp", LocalDateTime.now().toString());
            res.put("Failed", "No question with id " + questionId.toString() + " found in database, did you already delete it?");
            return new ResponseEntity<>(res,responseHeaders,HttpStatus.NOT_FOUND);
        }

    }



    @GetMapping("/questionset/user/{username}")
    public ResponseEntity< List<QuestionSetView> >getQuestionSetsByUser(@PathVariable("username") String username)
            throws EntityNotFoundException {
        URI location= URI.create("/questionset/user/" + username);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("user", username);
        return new ResponseEntity<List<QuestionSetView> >(questionViewService.getQuestionSetViewsByUser(username),
                responseHeaders, HttpStatus.OK);

    }

    @GetMapping("/questionsetanswered/user/{username}")
    public ResponseEntity<HashMap<Long, Boolean>>  checkWhichQuestionSetsAnsweredToday(@PathVariable("username") String username)
            throws EntityNotFoundException {
        HashMap<Long, Boolean> map = questionSetService.checkQuestionSetsForAnswered(username);
        URI location= URI.create("/questionset/user/" + username);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("user", username);
        return new ResponseEntity<HashMap<Long, Boolean>>(map,
                responseHeaders, HttpStatus.OK);
    }




}
