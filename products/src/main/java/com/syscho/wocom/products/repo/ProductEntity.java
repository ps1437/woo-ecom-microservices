package com.syscho.wocom.products.repo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity(name = "Products")
@Table(name = "Products")
public class ProductEntity {
    @Id
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
