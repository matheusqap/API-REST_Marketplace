package com.github.qualquercoisavinteconto.requests;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
  
  @JsonProperty("user_id")
  private Long userId;
  private String status;
  private List<PurchaseItemRequest> items;

}
