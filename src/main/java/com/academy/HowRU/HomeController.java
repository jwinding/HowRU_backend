package com.academy.HowRU;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String getHomePage(){
        return "The application works! Welcome to HowRU!";
    }

    @GetMapping("/secret")
    public String getSecretPage(){
        return "If you're seeing this, then the login seems to work";
    }
}
