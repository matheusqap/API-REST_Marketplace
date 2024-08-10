package com.github.qualquercoisavinteconto.services;

import java.util.List;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Ads;
import com.github.qualquercoisavinteconto.requests.AdsRequest;

public interface AdsService {
  
  Ads save(AdsRequest adsDTO);
  Ads findById(Long id) throws ResourceNotFoundException;
  List<Ads> findAll();
  Ads findByProductId(Long id);
  void delete(Long id);
  Ads update(Long id, AdsRequest adsDTO) throws ResourceNotFoundException;
  

}
