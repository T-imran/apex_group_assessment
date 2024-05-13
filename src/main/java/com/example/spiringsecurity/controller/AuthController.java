package com.example.spiringsecurity.controller;


import com.example.spiringsecurity.payload.request.AuthenticateRequest;
import com.example.spiringsecurity.payload.response.LoginResponse;
import com.example.spiringsecurity.payload.response.RegistrationResponse;
import com.example.spiringsecurity.service.AuthenticationService;
import com.example.spiringsecurity.payload.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authentication(@RequestBody AuthenticateRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }
}

