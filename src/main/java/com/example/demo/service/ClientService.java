package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
@Service
public class ClientService {
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> createClients(List<Client> clients){
        return clientRepository.saveAll(clients);
    }

    public List<Client> getAllClientsBySurname(String surname ) {
        return clientRepository.findBySurname(surname);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void updateClient(Long clientId, Client newClientData) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Клієнт не знайдений"));

        existingClient.setName(newClientData.getName());
        existingClient.setSurname(newClientData.getSurname());
        existingClient.setContactNumber(newClientData.getContactNumber());
        existingClient.setEmail(newClientData.getEmail());

        clientRepository.save(existingClient);
    }

    public boolean deleteClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            orderRepository.deleteAllByClient(clientOptional.get());
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Client> getClientsByOrderPeriodWithDetails(LocalDateTime startDate, LocalDateTime endDate) {
        return clientRepository.findClientsByOrderPeriodWithDetails(startDate, endDate);
    }

}

