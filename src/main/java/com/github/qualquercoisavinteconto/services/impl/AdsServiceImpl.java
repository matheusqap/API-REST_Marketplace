package com.github.qualquercoisavinteconto.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Ads;
import com.github.qualquercoisavinteconto.models.Product;
import com.github.qualquercoisavinteconto.repositories.AdsRepository;
import com.github.qualquercoisavinteconto.requests.AdsRequest;
import com.github.qualquercoisavinteconto.services.AdsService;
import com.github.qualquercoisavinteconto.services.ProductService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService{
  
  
  private final AdsRepository adsRepository;
  private final ProductService productService;

  @Override
  public Ads save(AdsRequest adsDTO) {
    Ads ads = new Ads();
    Product product = productService.findById(adsDTO.getProductId());
    ads.setProduct(product);
    ads.setDescription(adsDTO.getDescription());
    return adsRepository.save(ads);
  }

  @Override
  public Ads findById(Long id) throws ResourceNotFoundException {
    return adsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public List<Ads> findAll() {
    return adsRepository.findAll();
  }

  @Override
  public Ads findByProductId(Long id) {
    return adsRepository.findByProductId(id).orElseThrow(() -> new RuntimeException("Ads not found"));
  }

  @Override
  public void delete(Long id) {
    adsRepository.deleteById(id);
  }

  @Transactional
  public Ads update(Long id, AdsRequest adsDTO) throws ResourceNotFoundException {
    Ads ads = adsRepository.findById(id)
      .orElseThrow(ResourceNotFoundException::new);

    ads.setDescription(adsDTO.getDescription());
    
    Product product = productService.findById(adsDTO.getProductId());
    ads.setProduct(product);

    return adsRepository.save(ads);
  }

}
