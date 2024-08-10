package com.github.qualquercoisavinteconto.responses;

import java.util.List;

import lombok.Data;

@Data
public class UserSearchResponse {
    private Long id;
    private String email;
    private List<String> roles;
}
