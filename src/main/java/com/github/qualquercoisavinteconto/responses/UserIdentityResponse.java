package com.github.qualquercoisavinteconto.responses;

import java.util.List;

import com.github.qualquercoisavinteconto.models.Role;

import lombok.Data;

@Data
public class UserIdentityResponse {
    private Long id;
    private String name;
    private String email;
    private List<Role> roles;   
}
