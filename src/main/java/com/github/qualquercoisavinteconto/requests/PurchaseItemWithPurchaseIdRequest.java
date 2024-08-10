package com.github.qualquercoisavinteconto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItemWithPurchaseIdRequest {
  @JsonProperty("purchase_id")
  private Long purchaseId;

  @JsonProperty("product_id")  
  private Long productId;

  private Integer quantity;
  
}
