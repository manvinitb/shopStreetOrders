package com.shopstreet.backend.orders.controller;

import com.shopstreet.backend.orders.dto.CreateOrderRequestDTO;
import com.shopstreet.backend.orders.dto.CreateOrderRequestItemDTO;
import com.shopstreet.backend.orders.dto.CreateOrderResponseDTO;
import com.shopstreet.backend.orders.dto.OrderHistoryResponseDTO;
import com.shopstreet.backend.orders.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/oms")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @RequestMapping(value = "/order/create",method = RequestMethod.POST)
    public CreateOrderResponseDTO createOrder(@RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        return ordersService.createOrder(createOrderRequestDTO);
    }
    @RequestMapping(value = "/order/history/{userId}",method = RequestMethod.GET)
    public OrderHistoryResponseDTO createOrder(@PathVariable Long userId) {
        return ordersService.getOrderHistory(userId);
    }


}
