package com.github.qualquercoisavinteconto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.qualquercoisavinteconto.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> 
{    
}
