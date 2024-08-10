package com.github.qualquercoisavinteconto.services;

import java.util.List;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.models.Product;
import com.github.qualquercoisavinteconto.requests.ProductRequest;
import com.github.qualquercoisavinteconto.requests.ProductSearchRequest;
import com.github.qualquercoisavinteconto.responses.ProductSearchResponse;

public interface ProductService {
    public List<ProductSearchResponse> search(ProductSearchRequest request);
    public Product findById(Long id);
    public Product update(Long id, ProductRequest productRequest) throws ResourceNotFoundException;
    public void delete(Long id) throws ResourceNotFoundException;
    public Product save(ProductRequest productRequest);
}
