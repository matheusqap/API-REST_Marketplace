package com.github.qualquercoisavinteconto.services;

import java.util.List;

import com.github.qualquercoisavinteconto.models.Purchase;
import com.github.qualquercoisavinteconto.models.User;
import com.github.qualquercoisavinteconto.requests.PurchaseRequest;
import com.github.qualquercoisavinteconto.enums.PurchaseStatus;
import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;

public interface PurchaseService {
  
  Purchase save( PurchaseRequest purchaseDTO ) throws ResourceNotFoundException;
  Purchase findById( Long id ) throws ResourceNotFoundException;
  List<Purchase> findPurchasesByUser( User user );
  List<Purchase> findAll();
  void updateStatus( Long id, PurchaseStatus status );
  void delete( Long id );
  Purchase update( PurchaseRequest purchaseDTO, Long id );

}
