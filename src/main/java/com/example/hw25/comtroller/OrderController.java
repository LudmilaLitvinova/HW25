package com.example.hw25.comtroller;

import com.example.hw25.dto.OrderDto;
import com.example.hw25.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Ludmila Litvinova on 06.01
 */
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/orders/{id}")
    public OrderDto getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @GetMapping(value = "/orders")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping(value = "/orders")
    public void createOrder() {
        orderService.createOrder(new OrderDto(null, LocalDate.now(), 100, "Oil,bread"));
    }

    @DeleteMapping(value = "/orders/{id}")
    public void deleteOrderById(@PathVariable int id) {
        orderService.deleteOrderById(id);
    }


}
