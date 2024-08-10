package com.github.qualquercoisavinteconto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.qualquercoisavinteconto.models.Purchase;
import com.github.qualquercoisavinteconto.models.User;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

  List<Purchase> findByUser(User user);
  
}
