package com.github.qualquercoisavinteconto.services;

import com.github.qualquercoisavinteconto.exceptions.UserAlreadyExistsException;
import com.github.qualquercoisavinteconto.requests.SigninRequest;
import com.github.qualquercoisavinteconto.requests.SignupRequest;
import com.github.qualquercoisavinteconto.responses.SigninResponse;
import com.github.qualquercoisavinteconto.responses.SignupResponse;

public interface AuthService {
    SigninResponse signin(SigninRequest request);
    SignupResponse signup(SignupRequest request) throws UserAlreadyExistsException;
}
