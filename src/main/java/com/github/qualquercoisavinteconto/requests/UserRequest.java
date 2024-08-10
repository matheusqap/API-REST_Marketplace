package com.github.qualquercoisavinteconto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.github.qualquercoisavinteconto.models.Purchase;
import com.github.qualquercoisavinteconto.models.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String name;
    private String email;
    private List<Role> roles;
    private List<Purchase> purchases;
}
