package com.academy.HowRU.errorHandling.exceptions;

import lombok.Data;
import org.springframework.validation.Errors;

@Data
public class InputContentException extends Exception{
    private Errors validationErrors;

    public InputContentException(String message, Errors validationErrors){
        super(message);

        this.validationErrors = validationErrors;
    }
}
