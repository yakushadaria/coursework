package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    void deleteAllByClient(Client client);
    List<Order> findByClientId(Long clientId);
    List<Order> findByClient(Client client);
}
