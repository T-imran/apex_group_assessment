package com.example.spiringsecurity.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor
public class UserExceptions extends RuntimeException{

    private HttpStatus status;
    private String message;

}
