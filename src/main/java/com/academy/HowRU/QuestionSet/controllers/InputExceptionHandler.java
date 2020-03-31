package com.academy.HowRU.QuestionSet.controllers;


import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InputExceptionHandler extends ResponseEntityExceptionHandler {


    private final Logger log = LoggerFactory.getLogger(InputExceptionHandler.class);



//    @Override
//    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
//                                                          HttpStatus status, WebRequest request) {
//        log.error(ex.getMessage(), ex);
//
//        FieldError fieldError = ex.getBindingResult().getFieldError();
//        ResponseDTO responseDTO = ResponseDTO.builder()
//                .status(status.toString())
//                .message(fieldError.getDefaultMessage()).build();
//
//        return ResponseEntity.badRequest().body(responseDTO);
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public final ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
//        log.error(ex.getMessage(), ex);
//
//        ResponseDTO responseDTO = ResponseDTO.builder()
//                .status(HttpStatus.BAD_REQUEST.toString())
//                .message(ex.getMessage()).build();
//
//        return ResponseEntity.badRequest().body(responseDTO);
//    }
}
