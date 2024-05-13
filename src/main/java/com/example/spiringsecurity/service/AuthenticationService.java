package com.example.spiringsecurity.service;

import com.example.spiringsecurity.exception.UserExceptions;
import com.example.spiringsecurity.model.Role;
import com.example.spiringsecurity.model.User;
import com.example.spiringsecurity.payload.request.AuthenticateRequest;
import com.example.spiringsecurity.payload.request.RegisterRequest;
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


    public RegistrationResponse register(RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        if (request.getRole().equals("ADMIN")) {
            user.setRole(Role.ADMIN);
        }
        if (request.getRole().equals("MANAGER")) {
            user.setRole(Role.MANAGER);
        }
        if (request.getRole().equals("USER")) {
            user.setRole(Role.USER);
        }
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
            String jwtToken = jwtService.generateToken(user);
            return new RegistrationResponse(jwtToken, false);
        }
        return new RegistrationResponse(true);

    }

    public LoginResponse authenticate(AuthenticateRequest request) {

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
                loginResponse.setRole(user.getRole());
                loginResponse.setEmail(user.getEmail());
                loginResponse.setToken(token);

            }

        } catch (BadCredentialsException e) {
            throw new UserExceptions(HttpStatus.UNAUTHORIZED, "User id or Password is wrong");
        }

        return loginResponse;
    }
}
