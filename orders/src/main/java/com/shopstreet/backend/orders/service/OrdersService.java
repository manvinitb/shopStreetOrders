package com.shopstreet.backend.orders.service;

import com.shopstreet.backend.orders.dto.CreateOrderRequestDTO;
import com.shopstreet.backend.orders.dto.CreateOrderRequestItemDTO;
import com.shopstreet.backend.orders.dto.CreateOrderResponseDTO;
import com.shopstreet.backend.orders.dto.OrderHistoryResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrdersService {
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO);

    public OrderHistoryResponseDTO getOrderHistory(Long userId);
}
