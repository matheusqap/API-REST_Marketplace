package com.github.qualquercoisavinteconto.responses;

import lombok.Data;

@Data
public class SignupResponse {
    private String accessToken;
    private UserIdentityResponse identity;
}
