package com.syscho.wocom.products;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ProductDTO {

    private Long productId;
    private String name;
    private String description;
    private double price;
    private String brand;
    private String category;
    private String imageUrl;
    private int stockQuantity;
    private boolean isAvailable;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}