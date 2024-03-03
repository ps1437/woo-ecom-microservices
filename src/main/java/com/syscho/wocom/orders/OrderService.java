package com.syscho.wocom.orders;

import java.util.List;

public interface OrderService {

    OrderDTO getOrderById(Long orderId);

    List<OrderDTO> getAllOrders();

    void createOrder(OrderDTO orderDTO);

}
