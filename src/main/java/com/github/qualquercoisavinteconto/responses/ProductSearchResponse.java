package com.github.qualquercoisavinteconto.responses;

import lombok.Data;

@Data
public class ProductSearchResponse {
    private Long id;
    private String name;
    private double price;
    private String imageUrl;
}
