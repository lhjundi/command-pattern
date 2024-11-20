package com.lhjundi.ordersystem.application.receiver;

import com.lhjundi.ordersystem.domain.model.Customer;
import com.lhjundi.ordersystem.domain.model.Order;
import com.lhjundi.ordersystem.domain.repository.order.OrderRepository;

import java.math.BigDecimal;

public class OrderReceiver {
    private final OrderRepository repository;

    public OrderReceiver(OrderRepository repository) {
        this.repository = repository;
    }

    public void processOperation(String orderId, String customerId, BigDecimal amount) {
        try {
            Customer customer = new Customer(
                    customerId,
                    STR."Customer \{customerId}",
                    "customer@email.com",
                    "123456789"
            );

            Order order = new Order(orderId, customer, amount);
            order.process();
            simulateProcessing(amount);
            order.complete();
            repository.save(order);
        } catch (Exception e) {
            throw new RuntimeException(STR."Process operation failed: \{e.getMessage()}", e);
        }
    }

    public void cancelOperation(String orderId) {
        try {
            Order order = repository.findById(orderId)
                    .orElseThrow(() -> new IllegalArgumentException(STR."Order not found: \{orderId}"));
            order.cancel();
            repository.save(order);
        } catch (Exception e) {
            throw new RuntimeException(STR."Cancel operation failed: \{e.getMessage()}", e);
        }
    }

    private void simulateProcessing(BigDecimal amount) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}
