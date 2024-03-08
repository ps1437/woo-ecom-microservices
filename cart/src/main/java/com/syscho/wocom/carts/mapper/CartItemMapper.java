package com.syscho.wocom.carts.mapper;

import com.syscho.wocom.carts.dto.CartItemDTO;
import com.syscho.wocom.carts.repo.CartItemEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemDTO toCartItemDTO(CartItemEntity cartItemEntity);

    List<CartItemDTO> toCartItemDTOList(List<CartItemEntity> cartItemEntities);
}
