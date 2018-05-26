package com.shopstreet.backend.orders.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItemResponseDTO {
        private Long pid;
        private Long mid;
        private Long qty;
        private Double price;
}
