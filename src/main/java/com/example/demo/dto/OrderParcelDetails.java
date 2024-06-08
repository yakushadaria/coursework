package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderParcelDetails {

    private Long orderId;
    private LocalDateTime orderDate;
    private ParcelDTO parcel;

    public OrderParcelDetails(Long orderId, LocalDateTime orderDate,  ParcelDTO parcel) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.parcel = parcel;
    }

    @Data
    public static class ParcelDTO {
        private Long parcelId;
        private double weight;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime shipmentDate;
        private String destination;
        private String parcelDescription;

        public ParcelDTO(Long parcelId, double weight, LocalDateTime shipmentDate, String destination, String parcelDescription) {
            this.parcelId = parcelId;
            this.weight = weight;
            this.shipmentDate = shipmentDate;
            this.destination = destination;
            this.parcelDescription = parcelDescription;
        }
    }
}
