package com.syscho.wocom.orders;

import com.syscho.wocom.orders.dto.OrderDTO;
import com.syscho.wocom.orders.mapper.OrderMapper;
import com.syscho.wocom.orders.repo.OrderEntity;
import com.syscho.wocom.orders.repo.OrderItemRepository;
import com.syscho.wocom.orders.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
    }


    public OrderDTO getOrderById(Long orderId) {
        // Fetch order entity from the repository
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

        // Map the order entity to DTO
        return orderMapper.toOrderDTO(orderEntity);
    }


    public List<OrderDTO> getAllOrders() {
        // Fetch all order entities from the repository
        List<OrderEntity> orderEntities = orderRepository.findAll();

        // Map the list of order entities to a list of DTOs
        return orderEntities.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }


    public void createOrder(OrderDTO orderDTO) {
        // Map the DTO to an order entity
        OrderEntity orderEntity = orderMapper.toOrderEntity(orderDTO);

        // Save the order entity to the repository
        orderRepository.save(orderEntity);
    }

}
