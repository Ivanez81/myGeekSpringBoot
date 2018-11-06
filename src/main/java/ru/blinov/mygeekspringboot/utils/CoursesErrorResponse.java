package ru.blinov.mygeekspringboot.utils;

import lombok.Data;

import java.time.LocalTime;

@Data
public class CoursesErrorResponse {
    private int status;
    private String message;
    private LocalTime timestamp;
}
