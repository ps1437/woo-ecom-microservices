package com.syscho.wocom.carts.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CartDTO {

    private Long userId;
    private List<CartItemDTO> cartItems;
    private double total;

}
