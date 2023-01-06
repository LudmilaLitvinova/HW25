package com.example.hw25;

import com.example.hw25.dto.OrderDto;
import com.example.hw25.model.Product;
import com.example.hw25.repository.ProductRepository;
import com.example.hw25.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import java.time.LocalDate;

/**
 * @author Ludmila Litvinova on 06.01
 */
@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Hw25Application {
    private final OrderService orderService;
    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(Hw25Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void inited() {
        OrderDto ord = new OrderDto(null, LocalDate.now(), 100, "Oil,bread");
        orderService.createOrder(ord);
        log.info("ID={}", ord.getId());

        Integer orderId = orderService.getFirstOrderId();
        orderService.deleteOrderById(orderId);

        productRepository.save(new Product(null, "bread", 20));


    }
}