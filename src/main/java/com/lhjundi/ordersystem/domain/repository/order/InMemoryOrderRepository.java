package com.lhjundi.ordersystem.domain.repository.order;

import com.lhjundi.ordersystem.domain.model.Order;

import java.util.*;

public class InMemoryOrderRepository implements OrderRepository {

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
