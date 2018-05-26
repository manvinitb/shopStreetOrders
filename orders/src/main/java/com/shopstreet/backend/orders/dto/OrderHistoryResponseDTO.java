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
public class OrderHistoryResponseDTO {
    private Long userId;
    private List<OrderResponseDTO> orders;
}
