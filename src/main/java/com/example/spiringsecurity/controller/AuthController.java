package com.example.spiringsecurity.controller;


import com.example.spiringsecurity.payload.request.AuthenticateRequest;
import com.example.spiringsecurity.payload.response.ApiResponse;
import com.example.spiringsecurity.payload.response.LoginResponse;
import com.example.spiringsecurity.payload.response.RegistrationResponse;
import com.example.spiringsecurity.service.AuthenticationService;
import com.example.spiringsecurity.payload.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        ApiResponse apiResponse = authenticationService.register(request);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse> authentication(@RequestBody AuthenticateRequest request) {
        ApiResponse apiResponse =authenticationService.authenticate(request);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
}

