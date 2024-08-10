package com.github.qualquercoisavinteconto.services;

import java.util.List;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.User;
import com.github.qualquercoisavinteconto.responses.UserSearchResponse;

public interface UserService {

  User save(User user);
  User findById(Long id) throws ResourceNotFoundException;
  List<UserSearchResponse> findAll();
  void delete(Long id) throws ResourceNotFoundException;
  
}
