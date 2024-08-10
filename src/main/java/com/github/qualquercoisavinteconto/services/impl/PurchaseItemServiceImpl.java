package com.github.qualquercoisavinteconto.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Purchase;
import com.github.qualquercoisavinteconto.models.PurchaseItem;
import com.github.qualquercoisavinteconto.repositories.PurchaseItemRepository;
import com.github.qualquercoisavinteconto.repositories.PurchaseRepository;
import com.github.qualquercoisavinteconto.requests.PurchaseItemWithPurchaseIdRequest;
import com.github.qualquercoisavinteconto.requests.PurchaseItemRequest;
import com.github.qualquercoisavinteconto.services.ProductService;
import com.github.qualquercoisavinteconto.services.PurchaseItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseItemServiceImpl implements PurchaseItemService{

    private final ProductService productService;
    private final PurchaseItemRepository purchaseItemRepository;
    private final PurchaseRepository purchaseRepository;

    @Override
    @Transactional
    public PurchaseItem save(PurchaseItemWithPurchaseIdRequest purchaseItemDTO) {
        PurchaseItem purchaseItem = new PurchaseItem();
        purchaseItem.setProduct(productService.findById(purchaseItemDTO.getProductId()));
        purchaseItem.setQuantity(purchaseItemDTO.getQuantity());
        purchaseItem.setPurchase(purchaseRepository.findById(purchaseItemDTO.getPurchaseId()).orElse(null));
        purchaseItemRepository.save(purchaseItem);
        // recalcular total de purchase
        Purchase purchase = purchaseItem.getPurchase();
        List<PurchaseItem> purchaseItems = purchase.getPurchaseItems();
        Double total = purchaseItems.stream()
            .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
            .sum();
        purchase.setTotal(total);
        purchaseRepository.save(purchase);
        return purchaseItem;
    }
    
    @Override
    public void delete(Long id) {
        purchaseItemRepository.deleteById(id);
    }

    @Override
    public PurchaseItem update(PurchaseItemRequest purchaseItemDTO, Long id) {
        PurchaseItem purchaseItem = purchaseItemRepository.findById(id).orElse(null);
        if (purchaseItem != null) {
            purchaseItem.setProduct(productService.findById(purchaseItemDTO.getProductId()));
            purchaseItem.setQuantity(purchaseItemDTO.getQuantity());
            purchaseItemRepository.save(purchaseItem);
            // recalcular total de purchase
            Purchase purchase = purchaseItem.getPurchase();
            List<PurchaseItem> purchaseItems = purchase.getPurchaseItems();
            Double total = purchaseItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
            purchase.setTotal(total);
            purchaseRepository.save(purchase);
            return purchaseItem;
        }
        return null;
    }

    @Override
    public void deleteAllByPurchaseId(Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElse(null);
        if (purchase != null) {
            for (PurchaseItem purchaseItem : purchase.getPurchaseItems()) {
                purchaseItemRepository.deleteById(purchaseItem.getId());
            }
        }
    }

    @Override
    public List<PurchaseItem> findItemsByPurchaseId(Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElse(null);
        if (purchase != null) {
            return purchase.getPurchaseItems();
        }
        return List.of();
    }

    @Override
    public PurchaseItem findById(Long id) throws ResourceNotFoundException {
        return purchaseItemRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<PurchaseItem> findAll() {
        return purchaseItemRepository.findAll();
    }
}
