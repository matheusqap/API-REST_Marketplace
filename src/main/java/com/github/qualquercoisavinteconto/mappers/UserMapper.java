package com.github.qualquercoisavinteconto.mappers;

import com.github.qualquercoisavinteconto.models.Role;
import com.github.qualquercoisavinteconto.models.User;
import com.github.qualquercoisavinteconto.responses.UserIdentityResponse;
import com.github.qualquercoisavinteconto.responses.UserSearchResponse;

public class UserMapper {
    public static UserSearchResponse mapToSearchResponse(User user) {
        UserSearchResponse response = new UserSearchResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRoles(user.getRoles().stream().map(Role::getName).toList());
        return response;
    }    

    public static UserIdentityResponse mapToIdentity(User user) {
        UserIdentityResponse response = new UserIdentityResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRoles(user.getRoles());
        return response;
    }
}
