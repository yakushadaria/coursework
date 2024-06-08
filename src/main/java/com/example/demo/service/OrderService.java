package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.Order;
import com.example.demo.model.Parcel;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    private ClientRepository clientRepository;
    private ParcelRepository parcelRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, ParcelRepository parcelRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.parcelRepository = parcelRepository;
    }

    public List<Order> createOrders(List<Order> orders) {
        return orderRepository.saveAll(orders);
    }

    public List<Order> getAllOrdersWithDetails() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderOptional.orElse(null);
    }

    public boolean deleteOrderById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Получение всех заказов определенного клиента
    public List<Order> getOrdersByClientId(Long clientId) {
        return orderRepository.findByClientId(clientId);
    }

    public List<Order> getOrdersWithParcelWeightLessThan5kg() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .filter(order -> order.getParcels().getWeight() < 5)
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersByClient(Client client) {
        return orderRepository.findByClient(client);
    }

}