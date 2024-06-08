package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Table(name = "`order`")
public class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime orderDate;

        @ManyToOne
        @JoinColumn(name = "id_client")
        @JsonBackReference
        private Client client;

        @ManyToOne
        @JoinColumn(name = "id_parcels")
        @JsonBackReference
        private Parcel parcels;

        @Override
        public String toString() {
                return "Order{" +
                        "id=" + id +
                        ", orderDate=" + orderDate +
                        '}';
        }
    }

