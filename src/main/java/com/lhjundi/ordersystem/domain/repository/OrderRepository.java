package com.lhjundi.ordersystem.domain.repository;

import com.lhjundi.ordersystem.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    void save(Order order);
    Optional<Order> findById(String orderId);
    List<Order> findAll();
}
