package com.example.demo.repository;

import com.example.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findBySurname(String surname);
    @Query("SELECT DISTINCT c FROM Client c JOIN FETCH c.orders o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Client> findClientsByOrderPeriodWithDetails(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}

