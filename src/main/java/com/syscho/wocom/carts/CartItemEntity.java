package com.syscho.wocom.carts;

import com.syscho.wocom.products.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;


    private int quantity;

}
