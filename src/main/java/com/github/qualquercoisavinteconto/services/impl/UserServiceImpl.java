package com.github.qualquercoisavinteconto.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.mappers.UserMapper;
import com.github.qualquercoisavinteconto.models.User;
import com.github.qualquercoisavinteconto.repositories.UserRepository;
import com.github.qualquercoisavinteconto.responses.UserSearchResponse;
import com.github.qualquercoisavinteconto.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
  
  private final UserRepository userRepository;

  public List<UserSearchResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
            .map(UserMapper::mapToSearchResponse)
            .collect(Collectors.toList());
  }

  public User findById(Long id) throws ResourceNotFoundException {
    return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
  }  

  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public void delete(Long id) throws ResourceNotFoundException  {
    User user = this.findById(id);
    userRepository.delete(user);
  }
}
