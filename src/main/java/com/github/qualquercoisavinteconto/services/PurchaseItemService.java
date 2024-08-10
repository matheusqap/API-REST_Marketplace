package com.github.qualquercoisavinteconto.services;

import java.util.List;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.PurchaseItem;
import com.github.qualquercoisavinteconto.requests.PurchaseItemWithPurchaseIdRequest;
import com.github.qualquercoisavinteconto.requests.PurchaseItemRequest;

public interface PurchaseItemService {
  
  PurchaseItem save( PurchaseItemWithPurchaseIdRequest purchaseItemDTO );
  void delete( Long id );
  PurchaseItem update( PurchaseItemRequest purchaseItemDTO, Long id);
  void deleteAllByPurchaseId( Long purchaseId );
  List<PurchaseItem> findItemsByPurchaseId( Long purchaseId );
  PurchaseItem findById( Long id ) throws ResourceNotFoundException;
  List<PurchaseItem> findAll();

}
