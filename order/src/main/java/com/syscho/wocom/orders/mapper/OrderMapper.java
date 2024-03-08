package com.syscho.wocom.orders.mapper;

import com.syscho.wocom.orders.dto.OrderDTO;
import com.syscho.wocom.orders.repo.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {



    @Mapping(source = "orderItems", target = "orderItems")
    OrderDTO toOrderDTO(OrderEntity orderEntity);
    @Mapping(source = "orderItems", target = "orderItems", ignore = true)
    OrderEntity toOrderEntity(OrderDTO orderDTO);

    List<OrderDTO> toOrderDTOList(List<OrderEntity> orderEntities);

}
