package com.github.qualquercoisavinteconto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.qualquercoisavinteconto.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
  
  List<Review> findByProductId(Long id);
  List<Review> findByUserId(Long id);

}
