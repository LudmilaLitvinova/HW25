package com.example.hw25.repository;

import com.example.hw25.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Ludmila Litvinova on 06.01
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    Order getFirstByIdAfter(Integer id);
}
