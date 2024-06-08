package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private Long id;
    private LocalDateTime orderDate;
    private Long clientId;
    private Long parcelId;

    public OrderResponse(Long id, LocalDateTime orderDate, Long clientId, Long parcelId) {
        this.id = id;
        this.orderDate = orderDate;
        this.clientId = clientId;
        this.parcelId = parcelId;
    }
}

