package com.github.qualquercoisavinteconto.services;

import com.github.qualquercoisavinteconto.models.User;

public interface TokenService {
    String createToken(User user);
    String validateToken(String token);        
}
