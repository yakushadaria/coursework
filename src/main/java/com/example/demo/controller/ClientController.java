package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/clients")
    public ResponseEntity<?> createClients(@RequestBody List<Client> clients) {
       List<Client> createdClients = clientService.createClients(clients);
        return new ResponseEntity<>(createdClients, HttpStatus.CREATED);
    }


    @GetMapping ("/clients/surname")
    public List<Client> getClientsBySurname(@RequestParam String surname) {
        return clientService.getAllClientsBySurname(surname);
    }


    @PutMapping("/{clientId}")
    public ResponseEntity<String> updateClient(@PathVariable Long clientId, @RequestBody Client newClientData) {
        try {
            clientService.updateClient(clientId, newClientData);
            return ResponseEntity.ok("Дані клієнта успішно оновлено");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Помилка під час оновлення даних клієнта");
        }
    }


    @DeleteMapping("/clients/delete/{id}")
    public ResponseEntity<?> deleteClientById(@PathVariable Long id) {
        boolean isRemoved = clientService.deleteClientById(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Client with id " + id + " has been deleted", HttpStatus.OK);
    }


    @GetMapping("/clients/orders/period")
    public ResponseEntity<?> getClientsByOrderPeriod(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr)  {
        try {
            startDateStr = sanitizeInput(startDateStr);
            endDateStr = sanitizeInput(endDateStr);

            LocalDateTime startDate = LocalDateTime.parse(startDateStr);
            LocalDateTime endDate = LocalDateTime.parse(endDateStr);

            List<Client> clients = clientService.getClientsByOrderPeriodWithDetails(startDate, endDate);
            if (clients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>("Invalid date format", HttpStatus.BAD_REQUEST);
        }
    }

    private String sanitizeInput(String input) {
        return input.replaceAll("\\s+", "");
    }

}
