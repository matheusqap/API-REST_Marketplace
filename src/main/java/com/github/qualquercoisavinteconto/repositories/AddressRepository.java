package com.github.qualquercoisavinteconto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.qualquercoisavinteconto.models.Address;
import com.github.qualquercoisavinteconto.models.User;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

  List<Address> findByUser(User user);
  
}
