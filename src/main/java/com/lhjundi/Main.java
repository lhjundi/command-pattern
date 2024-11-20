package com.lhjundi;

import com.lhjundi.ordersystem.domain.model.Customer;
import com.lhjundi.ordersystem.domain.model.Order;
import com.lhjundi.ordersystem.domain.repository.GenericRepository;
import com.lhjundi.ordersystem.domain.repository.client.InMemoryCustomerRepository;
import com.lhjundi.ordersystem.domain.repository.order.InMemoryOrderRepository;
import com.lhjundi.ordersystem.presentation.OrderClient;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        GenericRepository<Order, String> orderRepository = new InMemoryOrderRepository();
        GenericRepository<Customer, String> customerRepository = new InMemoryCustomerRepository();
        OrderClient client = new OrderClient(orderRepository, customerRepository);

        try {
            // Create customer first
            client.createCustomer(
                    "CUST-001",
                    "John Doe",
                    "john.doe@example.com",
                    "123456789"
            );

            // Process order
            client.processOrder("ORD-001", "CUST-001", new BigDecimal("199.99"));
            System.out.println("Order processed successfully");

            // Try to cancel order
            try {
                client.cancelOrder("ORD-001");
                System.out.println("Order cancelled successfully");
            } catch (RuntimeException e) {
                System.out.println(STR."Cancel failed: \{e.getMessage()}");
            }

            // Verify final state
            orderRepository.findById("ORD-001").ifPresent(order -> {
                System.out.println("\nOrder details:");
                System.out.println(STR."ID: \{order.getId()}");
                System.out.println(STR."Customer: \{order.getCustomer().getName()}");
                System.out.println(STR."Amount: \{order.getAmount()}");
                System.out.println(STR."Status: \{order.getStatus()}");
                System.out.println("\nOrder history:");
                order.getStatusHistory().forEach(change ->
                        System.out.printf("Status: %s, Time: %s, Reason: %s%n",
                                change.status(),
                                change.changedAt(),
                                change.reason()
                        )
                );
            });

        } catch (Exception e) {
            System.err.println(STR."Error: \{e.getMessage()}");
            e.printStackTrace();
        }
    }
}