package com.github.qualquercoisavinteconto.services;

import java.util.List;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Role;
import com.github.qualquercoisavinteconto.requests.RoleStoreRequest;

public interface RoleService {

  List<Role> findAll();
  Role findById(Long id) throws ResourceNotFoundException;
  Role findByName(String name) throws ResourceNotFoundException;
  Role save(RoleStoreRequest role);
  void delete(Long id) throws ResourceNotFoundException;
  void update(Long id, RoleStoreRequest request) throws ResourceNotFoundException;
}
