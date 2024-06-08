package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDetailsDTO {
    private Long orderId;
    private LocalDateTime orderDate;
    private ClientDTO client;
    private ParcelDTO parcel;

    public OrderDetailsDTO(Long orderId, LocalDateTime orderDate,  ClientDTO client, ParcelDTO parcel) {
        this.orderId = orderId;
        this.orderDate = orderDate;

        this.client = client;
        this.parcel = parcel;
    }

    @Data
    public static class ClientDTO {
        private Long clientId;
        private String clientName;
        private String clientSurname;
        private String contactNumber;
        private String clientEmail;

        public ClientDTO(Long clientId, String clientName,String clientSurname, String contactNumber, String clientEmail) {
            this.clientId = clientId;
            this.clientName = clientName;
            this.clientSurname = clientSurname;
            this.contactNumber= contactNumber;
            this.clientEmail = clientEmail;
        }
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
