package com.shopstreet.backend.orders.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponseDTO {
    private Long id;
    private List<OrderItemResponseDTO> items;
    private Double amount;
}
