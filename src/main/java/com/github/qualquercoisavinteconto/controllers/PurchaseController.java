package com.github.qualquercoisavinteconto.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Purchase;
import com.github.qualquercoisavinteconto.models.User;
import com.github.qualquercoisavinteconto.requests.PurchaseRequest;
import com.github.qualquercoisavinteconto.services.PurchaseService;
import com.github.qualquercoisavinteconto.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@Tag(name = "Purchase")
@RequestMapping("/purchases")
public class PurchaseController {

  private final PurchaseService purchaseService;
  private UserService userService;

  public PurchaseController(PurchaseService purchaseService, UserService userService)
  {
    this.purchaseService = purchaseService;
    this.userService = userService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Purchase save(@RequestBody PurchaseRequest purchaseDTO) throws ResourceNotFoundException {
    return purchaseService.save(purchaseDTO);
  }

  @GetMapping
  public ResponseEntity<?> getPurchases() {
    List<Purchase> purchases = purchaseService.findAll();
    if(purchases.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No purchases found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(purchases);
  }

  @GetMapping("{id}")
  public ResponseEntity<Purchase> getPurchaseById(@PathVariable Long id) throws ResourceNotFoundException {
    return ResponseEntity.ok(purchaseService.findById(id));
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<?> getPurchasesByUser(@PathVariable Long userId) throws ResourceNotFoundException {
    User user = userService.findById(userId);
    if(user == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
    List<Purchase> purchases = purchaseService.findPurchasesByUser(user);
    if(purchases.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No purchases found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(purchases);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
    purchaseService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("{id}")
  public ResponseEntity<Purchase> updatePurchase(@PathVariable Long id, @RequestBody PurchaseRequest purchaseDTO) {
    return ResponseEntity.ok(purchaseService.update(purchaseDTO, id));
  }
}