package com.example.spiringsecurity.service;

import com.example.spiringsecurity.exception.UserExceptions;
import com.example.spiringsecurity.model.Role;
import com.example.spiringsecurity.model.User;
import com.example.spiringsecurity.payload.request.AuthenticateRequest;
import com.example.spiringsecurity.payload.request.RegisterRequest;
import com.example.spiringsecurity.payload.response.ApiResponse;
import com.example.spiringsecurity.payload.response.LoginResponse;
import com.example.spiringsecurity.payload.response.RegistrationResponse;
import com.example.spiringsecurity.repository.UserRepository;
import com.example.spiringsecurity.service.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public ApiResponse register(RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDesignation(request.getDesignation());
        user.setDeptMstCode(request.getDeptMstCode());
        user.setRole(Role.USER);

        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
            String jwtToken = jwtService.generateToken(user);
            return new ApiResponse(true,"Registration complete successfully",new RegistrationResponse(jwtToken, false),null) ;
        }
        return new ApiResponse(true,"Registration failed user already exist",new RegistrationResponse(true),null) ;
    }

    public ApiResponse authenticate(AuthenticateRequest request) {

        LoginResponse loginResponse = new LoginResponse();
        try {
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {

                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

                User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
                String token = jwtService.generateToken(user);

                loginResponse.setId(user.getId());
                loginResponse.setName(user.getName());
                loginResponse.setUserName(user.getUsername());
                loginResponse.setDesignation(user.getDesignation());
                loginResponse.setDeptMstCode(user.getDeptMstCode());
                loginResponse.setEmail(user.getEmail());
                loginResponse.setRole(user.getRole());
                loginResponse.setToken(token);

            }

        } catch (BadCredentialsException e) {
            throw new UserExceptions(HttpStatus.NOT_FOUND, "User id or Password is wrong");
        }

        return new ApiResponse(true,"Login successfully",loginResponse,null);
    }
}
