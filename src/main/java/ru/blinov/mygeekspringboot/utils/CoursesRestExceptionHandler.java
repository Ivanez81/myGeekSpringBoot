package ru.blinov.mygeekspringboot.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalTime;

@ControllerAdvice
public class CoursesRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<CoursesErrorResponse> handleAllException(Exception exc) {
        CoursesErrorResponse coursesErrorResponse = new CoursesErrorResponse();
        coursesErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        coursesErrorResponse.setMessage(exc.getMessage());
        coursesErrorResponse.setTimestamp(LocalTime.now());
        return new ResponseEntity<>(coursesErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
