package com.syscho.wocom.orders;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_items")
@Data
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @JoinColumn(name = "product_id", nullable = false)
    private Integer productId;

    @Column(nullable = false)
    private int quantity;

}