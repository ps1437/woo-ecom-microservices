package com.syscho.wocom.orders.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;
    private String customerName;
    private Timestamp orderDate;
    private List<OrderItemDTO> orderItems;

}
