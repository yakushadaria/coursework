package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.service.ClientService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ParcelService parcelService;

    @PostMapping
    public ResponseEntity<?> createOrders(@RequestBody List<OrderRequest> orderRequestDTOs) {
        List<Order> orderList = orderRequestDTOs.stream().map(dto -> {
            Order order = new Order();
            order.setOrderDate(dto.getOrderDate());
            order.setClient(clientService.getClientById(dto.getClientId()));
            order.setParcels(parcelService.getParcelById(dto.getParcelId()));
            return order;
        }).collect(Collectors.toList());

        List<Order> createdOrders = orderService.createOrders(orderList);

        List<OrderResponse> responseDTOs = createdOrders.stream().map(order ->
                new OrderResponse(order.getId(), order.getOrderDate(), order.getClient().getId(), order.getParcels().getId())
        ).collect(Collectors.toList());

        return new ResponseEntity<>(responseDTOs, HttpStatus.CREATED);
    }

    //5
    @GetMapping("/details")
    public ResponseEntity<?> getAllOrdersWithDetails() {
        List<Order> orders = orderService.getAllOrdersWithDetails();
        List<OrderDetailsDTO> orderDetailsDTOs = orders.stream().map(order -> new OrderDetailsDTO(
                order.getId(),
                order.getOrderDate(),
                new OrderDetailsDTO.ClientDTO(
                        order.getClient().getId(),
                        order.getClient().getName(),
                        order.getClient().getSurname(),
                        order.getClient().getContactNumber(),
                        order.getClient().getEmail()
                ),
                new OrderDetailsDTO.ParcelDTO(
                        order.getParcels().getId(),
                        order.getParcels().getWeight(),
                        order.getParcels().getShipmentDate(),
                        order.getParcels().getDestination(),
                        order.getParcels().getDescription()
                )
        )).collect(Collectors.toList());
        return new ResponseEntity<>(orderDetailsDTOs, HttpStatus.OK);
    }

//6
   @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                order.getId(),
                order.getOrderDate(),
                new OrderDetailsDTO.ClientDTO(
                        order.getClient().getId(),
                        order.getClient().getName(),
                        order.getClient().getSurname(),
                        order.getClient().getContactNumber(),
                        order.getClient().getEmail()
                ),
                new OrderDetailsDTO.ParcelDTO(
                        order.getParcels().getId(),
                        order.getParcels().getWeight(),
                        order.getParcels().getShipmentDate(),
                        order.getParcels().getDestination(),
                        order.getParcels().getDescription()
                )
        );
        return new ResponseEntity<>(orderDetailsDTO, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
        boolean isRemoved = orderService.deleteOrderById(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Order with id " + id + " has been deleted", HttpStatus.OK);
    }


    @GetMapping("/ByClientsId/{clientId}")
    public ResponseEntity<?> getOrdersByClientId(@PathVariable Long clientId) {
        List<Order> orders = orderService.getOrdersByClientId(clientId);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<OrderDetailsDTO> orderDetailsDTOs = orders.stream().map(order -> new OrderDetailsDTO(
                order.getId(),
                order.getOrderDate(),
                new OrderDetailsDTO.ClientDTO(
                        order.getClient().getId(),
                        order.getClient().getName(),
                        order.getClient().getSurname(),
                        order.getClient().getContactNumber(),
                        order.getClient().getEmail()
                ),
                new OrderDetailsDTO.ParcelDTO(
                        order.getParcels().getId(),
                        order.getParcels().getWeight(),
                        order.getParcels().getShipmentDate(),
                        order.getParcels().getDestination(),
                        order.getParcels().getDescription()
                )
        )).collect(Collectors.toList());

        return new ResponseEntity<>(orderDetailsDTOs, HttpStatus.OK);
    }

    @GetMapping("/withParcelWeightLessThan5kg")
    public List<OrderParcelDetails> getOrdersWithParcelWeightLessThan5kg() {
        List<Order> orders = orderService.getOrdersWithParcelWeightLessThan5kg();
        return orders.stream().map(order -> new OrderParcelDetails(
                        order.getId(),
                        order.getOrderDate(),
                        new OrderParcelDetails.ParcelDTO(
                                order.getParcels().getId(),
                                order.getParcels().getWeight(),
                                order.getParcels().getShipmentDate(),
                                order.getParcels().getDestination(),
                                order.getParcels().getDescription()
                        )
                ))
                .collect(Collectors.toList());
    }


    @GetMapping("/ordersByClient/{clientId}")
    public ResponseEntity<?> getOrdersByClient(@PathVariable Long clientId) {
        Client client = clientService.getClientById(clientId);
        List<Order> orders = orderService.getOrdersByClient(client);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<OrderClientDetails> orderDetailsDTOs = orders.stream().map(order -> new OrderClientDetails(
                            order.getId(),
                            order.getOrderDate(),
                            new OrderClientDetails.ClientDTO(
                                    order.getClient().getId(),
                                    order.getClient().getName(),
                                    order.getClient().getSurname(),
                                    order.getClient().getContactNumber(),
                                    order.getClient().getEmail()
                            )
                    ))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(orderDetailsDTOs, HttpStatus.OK);
        }
    }

}
