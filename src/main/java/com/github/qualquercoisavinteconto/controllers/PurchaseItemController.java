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

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.PurchaseItem;
import com.github.qualquercoisavinteconto.requests.PurchaseItemWithPurchaseIdRequest;
import com.github.qualquercoisavinteconto.requests.PurchaseItemRequest;
import com.github.qualquercoisavinteconto.services.PurchaseItemService;

@RestController
@Tag(name = "PurchaseItem")
@RequestMapping("/purchases-items")
public class PurchaseItemController {

  private PurchaseItemService purchaseItemService;

  public PurchaseItemController(PurchaseItemService purchaseItemService) {
    this.purchaseItemService = purchaseItemService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PurchaseItem save(@RequestBody PurchaseItemWithPurchaseIdRequest purchaseItemDTO) {
    return purchaseItemService.save(purchaseItemDTO);
  }

  @GetMapping
  public ResponseEntity<List<PurchaseItem>> getPurchaseItems() {
    List<PurchaseItem> purchaseItems = purchaseItemService.findAll();

    if(purchaseItems.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(purchaseItems);
  }

  @GetMapping("{id}")
  public ResponseEntity<PurchaseItem> getPurchaseItemById(@PathVariable Long id) throws ResourceNotFoundException {
    return ResponseEntity.ok(purchaseItemService.findById(id));
  }

  @GetMapping("/purchase/{purchaseId}")
  public ResponseEntity<List<PurchaseItem>> getPurchaseItemsByPurchaseId(@PathVariable Long purchaseId) throws ResourceNotFoundException {

    List<PurchaseItem> purchaseItems = purchaseItemService.findItemsByPurchaseId(purchaseId);
    
    if(purchaseItems.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(purchaseItems);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> deletePurchaseItem(@PathVariable Long id) {
    purchaseItemService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/purchase/{purchaseId}")
  public ResponseEntity<Void> deletePurchaseItemsByPurchaseId(@PathVariable Long purchaseId) {
    purchaseItemService.deleteAllByPurchaseId(purchaseId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("{id}")
  public ResponseEntity<PurchaseItem> updatePurchaseItem(@PathVariable Long id, @RequestBody PurchaseItemRequest purchaseItemDTO) {
    return ResponseEntity.ok(purchaseItemService.update(purchaseItemDTO, id));
  } 
}