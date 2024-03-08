package com.syscho.wocom.carts;

import lombok.Data;

@Data
public class CartItemDTO {

    private Long productId;
    private ProductDTO product;
    private int quantity;
    private double subtotal;

}
