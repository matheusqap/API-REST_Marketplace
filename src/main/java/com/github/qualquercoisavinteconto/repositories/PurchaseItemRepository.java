package com.github.qualquercoisavinteconto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.qualquercoisavinteconto.models.PurchaseItem;
import com.github.qualquercoisavinteconto.models.Purchase;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> 
{    
  List<PurchaseItem> findByPurchase(Purchase purchase);
}


