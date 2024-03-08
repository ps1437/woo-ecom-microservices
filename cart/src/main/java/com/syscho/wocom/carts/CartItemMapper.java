package com.syscho.wocom.carts;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemDTO toCartItemDTO(CartItemEntity cartItemEntity);

    List<CartItemDTO> toCartItemDTOList(List<CartItemEntity> cartItemEntities);
}
