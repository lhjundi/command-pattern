package com.lhjundi.ordersystem.domain.repository;

import com.lhjundi.ordersystem.domain.model.Order;

import java.util.*;

public final class InMemoryOrderRepository implements GenericRepository<Order, String> {

    private final Map<String, Order> orders = new HashMap<>();

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }
}
