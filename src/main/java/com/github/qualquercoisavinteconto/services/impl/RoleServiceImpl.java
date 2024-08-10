package com.github.qualquercoisavinteconto.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Role;
import com.github.qualquercoisavinteconto.repositories.RoleRepository;
import com.github.qualquercoisavinteconto.requests.RoleStoreRequest;
import com.github.qualquercoisavinteconto.services.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
  
  private final RoleRepository roleRepository;

  @Override
  public Role save(RoleStoreRequest request) {
    Role role = new Role();
    role.setName(request.getName().toUpperCase());
    return roleRepository.save(role);
  }

  @Override
  public Role findById(Long id) throws ResourceNotFoundException {
    return roleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public Role findByName(String name) throws ResourceNotFoundException {
    return roleRepository.findByNameContainingIgnoreCase(name).orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  @Override
  public void delete(Long id) throws ResourceNotFoundException {
    Role role = this.findById(id);
    roleRepository.delete(role);
  }

  @Override
  public void update(Long id, RoleStoreRequest request) throws ResourceNotFoundException {
    Role role = this.findById(id);
    role.setName(request.getName().toUpperCase());
    this.roleRepository.save(role);    
  }
}
