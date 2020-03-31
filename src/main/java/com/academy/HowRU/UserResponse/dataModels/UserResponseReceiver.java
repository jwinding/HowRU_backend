package com.academy.HowRU.UserResponse.dataModels;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseReceiver {

    private Long questionId;
    private String username;
    private String question;
    private Integer value;
    private String text;
    private String optionType;
    private LocalDateTime responseTime;

    public UserResponseReceiver(){
    }

    public UserResponseReceiver(Long questionId, String userName, String question, Integer value, String optionType) {
        this.questionId = questionId;
        this.username = userName;
        this.question = question;
        this.value = value;
        this.text = null;
        this.optionType = optionType;
        this.responseTime = null;
    }

    public UserResponseReceiver(Long questionId, String userName, String question, String text, String optionType) {
        this.questionId = questionId;
        this.username = userName;
        this.question = question;
        this.value = null;
        this.text = text;
        this.optionType = optionType;
        this.responseTime = null;
    }
}
