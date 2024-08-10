package com.github.qualquercoisavinteconto.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.qualquercoisavinteconto.models.Purchase;
import com.github.qualquercoisavinteconto.repositories.PurchaseItemRepository;
import com.github.qualquercoisavinteconto.repositories.PurchaseRepository;
import com.github.qualquercoisavinteconto.requests.PurchaseRequest;
import com.github.qualquercoisavinteconto.services.ProductService;
import com.github.qualquercoisavinteconto.services.PurchaseService;
import com.github.qualquercoisavinteconto.services.UserService;
import com.github.qualquercoisavinteconto.models.PurchaseItem;
import com.github.qualquercoisavinteconto.models.User;
import com.github.qualquercoisavinteconto.enums.PurchaseStatus;
import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService{
  
  private final PurchaseRepository purchaseRepository;
  private final PurchaseItemRepository purchaseItemRepository;
  private final ProductService productService;
  private final UserService userService;

  @Override
  @Transactional
  public Purchase save(PurchaseRequest purchaseDTO) throws ResourceNotFoundException {
    Long userId = purchaseDTO.getUserId();

    User user = userService.findById(userId);
    
    Double total = purchaseDTO.getItems().stream()
      .mapToDouble(item -> productService.findById(item.getProductId()).getPrice() * item.getQuantity())
      .sum();

    Purchase purchase = new Purchase();
    purchase.setUser(user);
    purchase.setTotal(total);
    purchase.setStatus(PurchaseStatus.valueOf(purchaseDTO.getStatus()));
    purchase.setCreatedAt(LocalDateTime.now());

    purchaseRepository.save(purchase);

    List<PurchaseItem> purchaseItems = purchaseDTO.getItems().stream()
      .map(item -> {
        PurchaseItem purchaseItem = new PurchaseItem();
        purchaseItem.setPurchase(purchase);
        purchaseItem.setProduct(productService.findById(item.getProductId()));
        purchaseItem.setQuantity(item.getQuantity());
        return purchaseItem;
      })
      .collect(Collectors.toList());

    purchaseItemRepository.saveAll(purchaseItems);

    return purchase;
  }

  @Override
  public Purchase findById(Long id) throws ResourceNotFoundException {
    return purchaseRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public List<Purchase> findPurchasesByUser(User user) {
    return purchaseRepository.findByUser(user);
  }

  @Override
  public void updateStatus(Long id, PurchaseStatus status) {
    Purchase purchase = purchaseRepository.findById(id).orElseThrow();
    purchase.setStatus(status);
    purchaseRepository.save(purchase);
  }

  @Override
  public void delete(Long id) {
    purchaseRepository.deleteById(id);
  }

  @Override
  public List<Purchase> findAll() {
    return purchaseRepository.findAll();
  }

  @Override
  public Purchase update(PurchaseRequest purchaseDTO, Long id) {
    Purchase purchase = purchaseRepository.findById(id).orElseThrow();
    purchase.setStatus(PurchaseStatus.valueOf(purchaseDTO.getStatus()));
    purchase.setTotal(purchaseDTO.getItems().stream()
      .mapToDouble(item -> productService.findById(item.getProductId()).getPrice() * item.getQuantity())
      .sum());
    purchaseRepository.save(purchase);

    List<PurchaseItem> purchaseItems = purchaseItemRepository.findByPurchase(purchase);
    purchaseItemRepository.deleteAll(purchaseItems);

    purchaseItems = purchaseDTO.getItems().stream()
      .map(item -> {
        PurchaseItem purchaseItem = new PurchaseItem();
        purchaseItem.setPurchase(purchase);
        purchaseItem.setProduct(productService.findById(item.getProductId()));
        purchaseItem.setQuantity(item.getQuantity());
        return purchaseItem;
      })
      .collect(Collectors.toList());

    purchaseItemRepository.saveAll(purchaseItems);

    return purchase;
  }

}


