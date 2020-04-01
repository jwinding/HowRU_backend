package com.academy.HowRU.UserResponse.inputModels;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseReceiver {

    private Long optionId;
    private String username;
    private Integer value;
    private String text;

    public UserResponseReceiver(){
    }

    public UserResponseReceiver(Long optionId, String userName, Integer value, String text) {
        this.username = userName;
        this.value = value;
        this.text = text;
        this.optionId = optionId;
    }

}
