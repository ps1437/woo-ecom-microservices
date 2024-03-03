package com.syscho.wocom.carts;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {

    private Long userId;
    private List<CartItemDTO> cartItems;
    private double total;

}
