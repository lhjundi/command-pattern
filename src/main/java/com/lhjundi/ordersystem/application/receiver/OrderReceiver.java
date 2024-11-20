package com.lhjundi.ordersystem.application.receiver;

import com.lhjundi.ordersystem.domain.model.Customer;
import com.lhjundi.ordersystem.domain.model.Order;
import com.lhjundi.ordersystem.domain.repository.client.CustomerRepository;
import com.lhjundi.ordersystem.domain.repository.order.OrderRepository;

import java.math.BigDecimal;

public class OrderReceiver {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderReceiver(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public void processOperation(String orderId, String customerId, BigDecimal amount) {
        try {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            STR."Customer not found: \{customerId}"));

            Order order = new Order(orderId, customer, amount);
            order.process();
            simulateProcessing(amount);
            order.complete();
            orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException(STR."Process operation failed: \{e.getMessage()}", e);
        }
    }

    public void cancelOperation(String orderId) {
        try {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new IllegalArgumentException(STR."Order not found: \{orderId}"));
            order.cancel();
            orderRepository.save(order);
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
