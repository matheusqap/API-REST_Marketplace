package com.github.qualquercoisavinteconto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SigninResponse {
    private String accessToken;
    private UserIdentityResponse identity;
}
