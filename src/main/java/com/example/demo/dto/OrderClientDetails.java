package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderClientDetails {
    private Long orderId;
    private LocalDateTime orderDate;
    private ClientDTO client;

    public OrderClientDetails(Long orderId, LocalDateTime orderDate, ClientDTO client) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.client = client;

    }

    @Data
    public static class ClientDTO {
        private Long clientId;
        private String clientName;
        private String clientSurname;
        private String contactNumber;
        private String clientEmail;

        public ClientDTO(Long clientId, String clientName, String clientSurname, String contactNumber, String clientEmail) {
            this.clientId = clientId;
            this.clientName = clientName;
            this.clientSurname = clientSurname;
            this.contactNumber = contactNumber;
            this.clientEmail = clientEmail;
        }
    }


}


