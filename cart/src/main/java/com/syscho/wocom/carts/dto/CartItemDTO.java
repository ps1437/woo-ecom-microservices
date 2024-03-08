package com.syscho.wocom.carts.dto;

import com.syscho.wocom.carts.products.ProductDTO;
import lombok.Data;

@Data
public class CartItemDTO {

    private Long productId;
    private ProductDTO product;
    private int quantity;
    private double subtotal;

}
