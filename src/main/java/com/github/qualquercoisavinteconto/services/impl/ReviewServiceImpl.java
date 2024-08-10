package com.github.qualquercoisavinteconto.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Product;
import com.github.qualquercoisavinteconto.models.Review;
import com.github.qualquercoisavinteconto.models.User;
import com.github.qualquercoisavinteconto.repositories.ReviewRepository;
import com.github.qualquercoisavinteconto.requests.ReviewRequest;
import com.github.qualquercoisavinteconto.services.ProductService;
import com.github.qualquercoisavinteconto.services.ReviewService;
import com.github.qualquercoisavinteconto.services.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
  
  private final ReviewRepository reviewRepository;
  private final ProductService productService;
  private final UserService userService;

  @Override
  public Review save(ReviewRequest reviewDTO) throws ResourceNotFoundException {
    Product product = productService.findById(reviewDTO.getProductId());
    User user = userService.findById(reviewDTO.getUserId());
    Review review = new Review();
    review.setDescription(reviewDTO.getDescription());
    review.setStars(reviewDTO.getStars());
    review.setProduct(product);
    review.setUser(user);
    return reviewRepository.save(review);
  }

  @Override
  public Review findById(Long id) throws ResourceNotFoundException {
    return reviewRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public List<Review> findAll() {
    return reviewRepository.findAll();
  }

  @Override
  public List<Review> findByProductId(Long id) {
    return reviewRepository.findByProductId(id);
  }

  @Override
  public List<Review> findByUserId(Long id) {
    return reviewRepository.findByUserId(id);
  }

  @Override
  public void delete(Long id) throws ResourceNotFoundException {
    Review review = this.findById(id);
    reviewRepository.delete(review);
  }

  @Transactional
  public Review update(Long id, ReviewRequest reviewDTO) throws ResourceNotFoundException {
      Review review = this.findById(id);

      review.setDescription(reviewDTO.getDescription());
      review.setStars(reviewDTO.getStars());
      
      Product product = productService.findById(reviewDTO.getProductId());
      review.setProduct(product);

      User user = userService.findById(reviewDTO.getUserId());
      review.setUser(user);

      return reviewRepository.save(review);
  }

}
