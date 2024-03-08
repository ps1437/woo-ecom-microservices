package com.syscho.wocom.carts.repo;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name ="cart_items" )
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;

    @Column(name = "product_id", nullable = false)  // Add a direct reference to productId
    private Long productId;


    private int quantity;

}
