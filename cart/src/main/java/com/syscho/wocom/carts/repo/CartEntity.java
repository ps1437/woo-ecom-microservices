package com.syscho.wocom.carts.repo;

import com.syscho.wocom.carts.repo.CartItemEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name ="cart" )
@Data
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartItemEntity> cartItems;

    private double total;

}
