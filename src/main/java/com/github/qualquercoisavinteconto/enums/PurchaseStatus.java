package com.github.qualquercoisavinteconto.enums;

public enum PurchaseStatus {
  
  APPROVED("approved"),
  REJECTED("rejected");

  private String status;

  PurchaseStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public static PurchaseStatus fromString(String status) {
    for (PurchaseStatus purchaseStatus : PurchaseStatus.values()) {
      if (purchaseStatus.getStatus().equalsIgnoreCase(status)) {
        return purchaseStatus;
      }
    }
    throw new IllegalArgumentException("Invalid PurchaseStatus value: " + status);
  }

}
