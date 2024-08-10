package com.github.qualquercoisavinteconto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.qualquercoisavinteconto.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> 
{    

  Optional<Role> findByNameContainingIgnoreCase(String name);

}
