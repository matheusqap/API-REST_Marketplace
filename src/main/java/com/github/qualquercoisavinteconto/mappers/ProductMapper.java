package com.github.qualquercoisavinteconto.mappers;

import com.github.qualquercoisavinteconto.models.Product;
import com.github.qualquercoisavinteconto.responses.ProductSearchResponse;

public class ProductMapper {
    private static final String LOREM_PICSUM_URL_TEMPLATE = "https://picsum.photos/seed/%d/200/300";

    public static ProductSearchResponse mapToSearchResponse(Product product) {
        ProductSearchResponse response = new ProductSearchResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setImageUrl(getRandomImageUrl(product.getId()));
        return response;
    }

    private static String getRandomImageUrl(Long productId) {
        return String.format(LOREM_PICSUM_URL_TEMPLATE, productId);
    }
}
