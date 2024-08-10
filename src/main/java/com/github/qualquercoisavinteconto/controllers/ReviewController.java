package com.github.qualquercoisavinteconto.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.qualquercoisavinteconto.services.impl.ReviewServiceImpl;
import com.github.qualquercoisavinteconto.models.Review;
import com.github.qualquercoisavinteconto.requests.ReviewRequest;
import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;

@RestController
@Tag(name = "Review")
@RequestMapping("reviews")
public class ReviewController {

    private final ReviewServiceImpl reviewService;

    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review save(@RequestBody ReviewRequest reviewDTO) throws ResourceNotFoundException {
        ReviewRequest review = new ReviewRequest();
        review.setDescription(reviewDTO.getDescription());
        review.setStars(reviewDTO.getStars());
        review.setUserId(reviewDTO.getUserId());
        review.setProductId(reviewDTO.getProductId());
        return reviewService.save(review);
    }

    @GetMapping("{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) throws ResourceNotFoundException {
        Review review = reviewService.findById(id);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<Review>> getListByUserId(@PathVariable Long id) {
        List<Review> reviews = reviewService.findByUserId(id);
        if(reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Review> update(@PathVariable Long id, @RequestBody ReviewRequest reviewDTO) throws ResourceNotFoundException {
        var review = reviewService.update(id, reviewDTO);
        return ResponseEntity.ok(review);
    }
}