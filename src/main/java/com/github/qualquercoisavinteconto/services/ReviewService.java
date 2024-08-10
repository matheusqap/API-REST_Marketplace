package com.github.qualquercoisavinteconto.services;

import java.util.List;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Review;
import com.github.qualquercoisavinteconto.requests.ReviewRequest;

public interface ReviewService {
  
  Review save(ReviewRequest reviewDTO) throws ResourceNotFoundException;
  Review findById(Long id) throws ResourceNotFoundException;
  List<Review> findAll();
  List<Review> findByProductId(Long id);
  List<Review> findByUserId(Long id);
  void delete(Long id) throws ResourceNotFoundException;
  Review update(Long id, ReviewRequest reviewDTO) throws ResourceNotFoundException;

}
