package com.github.qualquercoisavinteconto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.qualquercoisavinteconto.exceptions.UserAlreadyExistsException;
import com.github.qualquercoisavinteconto.requests.SigninRequest;
import com.github.qualquercoisavinteconto.requests.SignupRequest;
import com.github.qualquercoisavinteconto.responses.SigninResponse;
import com.github.qualquercoisavinteconto.responses.SignupResponse;
import com.github.qualquercoisavinteconto.services.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/signin")
    public ResponseEntity<SigninResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(this.service.signin(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest request) throws UserAlreadyExistsException {
        return ResponseEntity.ok(this.service.signup(request));
    }
}
