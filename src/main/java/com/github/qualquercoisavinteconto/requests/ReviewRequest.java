package com.github.qualquercoisavinteconto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
  
  private String description;
  private Integer stars;
  @JsonProperty("product_id")
  private Long productId;
  @JsonProperty("user_id")
  private Long userId;

}
