package com.example.hw25.service;

import com.example.hw25.dto.OrderDto;
import com.example.hw25.model.Order;
import com.example.hw25.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Ludmila Litvinova on 06.01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = objectMapper.convertValue(orderDto, Order.class);
        orderRepository.save(order);
        log.info("Added Order = " + order.getId());
        orderDto.setId(order.getId());
        return orderDto;
    }

    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> listOrders = (List<Order>) orderRepository.findAll();
        for (Order order : listOrders) {
            orderDtoList.add(objectMapper.convertValue(order, OrderDto.class));
        }
        return orderDtoList;
    }

    public OrderDto getOrderById(int id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        Order order = orderOptional.orElseThrow(() -> new EntityNotFoundException("Order wasn`t found by id=" + id));
        return objectMapper.convertValue(order, OrderDto.class);
    }

    public void deleteOrderById(int id) {
        orderRepository.deleteById(id);
        log.info("Delete at Order = " + id);
    }

    public Integer getFirstOrderId() {
        return orderRepository.getFirstByIdAfter(-46).getId();
    }

}
