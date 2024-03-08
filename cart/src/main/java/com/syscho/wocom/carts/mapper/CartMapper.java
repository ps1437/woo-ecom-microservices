package com.syscho.wocom.carts.mapper;

import com.syscho.wocom.carts.dto.CartDTO;
import com.syscho.wocom.carts.dto.CartItemDTO;
import com.syscho.wocom.carts.repo.CartEntity;
import com.syscho.wocom.carts.repo.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(source = "cartItems", target = "cartItems")
    CartDTO toCartDTO(CartEntity cartEntity);

    @Mapping(source = "cartItems", target = "cartItems", ignore = true)
    CartEntity toCartEntity(CartDTO cartDTO);

    CartItemDTO toCartItemDTO(CartItemEntity cartItemEntity);

    CartItemEntity toCartItemEntity(CartItemDTO cartItemDTO);
}
