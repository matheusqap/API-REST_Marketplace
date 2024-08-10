package com.github.qualquercoisavinteconto.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.qualquercoisavinteconto.exceptions.ResourceNotFoundException;
import com.github.qualquercoisavinteconto.mappers.ProductMapper;
import com.github.qualquercoisavinteconto.models.Category;
import com.github.qualquercoisavinteconto.models.Product;
import com.github.qualquercoisavinteconto.repositories.CategoryRepository;
import com.github.qualquercoisavinteconto.repositories.ProductRepository;
import com.github.qualquercoisavinteconto.requests.ProductRequest;
import com.github.qualquercoisavinteconto.requests.ProductSearchRequest;
import com.github.qualquercoisavinteconto.responses.ProductSearchResponse;
import com.github.qualquercoisavinteconto.services.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository)
    {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductSearchResponse> search(ProductSearchRequest request) 
    {
        List<Product> products = productRepository.findAll();
        return products.stream()
            .map(ProductMapper::mapToSearchResponse)
            .collect(Collectors.toList());
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public Product update(Long id, ProductRequest productRequest) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
            .orElseThrow(ResourceNotFoundException::new);

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        List<Category> categories = categoryRepository.findAllById(productRequest.getCategories());
        product.setCategories(categories);

        return productRepository.save(product);
    }

    public void delete(Long id) throws ResourceNotFoundException {
        Product product = this.productRepository.findById(id)
            .orElseThrow(ResourceNotFoundException::new);

        productRepository.delete(product);
    }

    @Transactional
    public Product save(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        List<Category> categories = categoryRepository.findAllById(productRequest.getCategories());
        product.setCategories(categories);

        return productRepository.save(product);
    }    
}
