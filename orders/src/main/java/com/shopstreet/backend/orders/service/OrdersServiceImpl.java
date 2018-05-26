package com.shopstreet.backend.orders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopstreet.backend.orders.dao.Order;
import com.shopstreet.backend.orders.dto.*;
import com.shopstreet.backend.orders.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ObjectMapper objectMapper;



    @Override
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO) {
        Order order = createOrderInInitialState(createOrderRequestDTO);
        order = ordersRepository.save(order);
        return new CreateOrderResponseDTO(order.getId());
    }



    private Order createOrderInInitialState(CreateOrderRequestDTO createOrderRequestDTO) {
        Order.OrderBuilder builder = Order.builder();
        builder.userid(createOrderRequestDTO.getUserId());
        builder.cartid(createOrderRequestDTO.getCartid());
        List<CreateOrderRequestItemDTO> items = createOrderRequestDTO.getItems();
        String itemDetails="" ;
        try {
            itemDetails = objectMapper.writeValueAsString(items);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            builder.itemdetail(itemDetails);
        }

        BigDecimal totalAmount = BigDecimal.ZERO;
        for(CreateOrderRequestItemDTO itemDTO: items){
            totalAmount = totalAmount.add(BigDecimal.valueOf(itemDTO.getPrice()));
        }
        builder.amount(totalAmount.doubleValue());
        return builder.build();
    }

    @Override
    public OrderHistoryResponseDTO getOrderHistory(Long userId) {
        List<Order> orderList = ordersRepository.findAllByUseridOrderByIdDesc(userId);
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        for(Order order:orderList){
            orderResponseDTOList.add(transformOrder(order));
        }
        OrderHistoryResponseDTO historyResponseDTO = new OrderHistoryResponseDTO(userId, orderResponseDTOList);
        return historyResponseDTO;
    }

    private OrderResponseDTO transformOrder(Order order) {
        OrderResponseDTO.OrderResponseDTOBuilder builder = OrderResponseDTO.builder();
        builder.amount(order.getAmount());
        builder.id(order.getId());
        builder.items(transformItemDetails(order.getItemdetail()));
        return builder.build();
    }

    private List<OrderItemResponseDTO> transformItemDetails(String itemDetails) {
        List<OrderItemResponseDTO> items = Collections.emptyList();
        try{
            items = objectMapper.readValue(itemDetails, new TypeReference<List<OrderItemResponseDTO>>(){});
        } catch(IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}
