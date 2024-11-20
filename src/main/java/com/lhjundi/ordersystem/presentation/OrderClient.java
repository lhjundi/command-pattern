package com.lhjundi.ordersystem.presentation;

import com.lhjundi.ordersystem.application.command.CancelOrderCommand;
import com.lhjundi.ordersystem.application.command.Command;
import com.lhjundi.ordersystem.application.command.ProcessOrderCommand;
import com.lhjundi.ordersystem.application.invoker.OrderInvoker;
import com.lhjundi.ordersystem.application.receiver.OrderReceiver;
import com.lhjundi.ordersystem.domain.model.Customer;
import com.lhjundi.ordersystem.domain.model.Order;
import com.lhjundi.ordersystem.domain.repository.GenericRepository;

import java.math.BigDecimal;

public final class OrderClient {
    private final OrderReceiver receiver;
    private final OrderInvoker invoker;
    private final GenericRepository<Customer, String> customerRepository;

    public OrderClient(GenericRepository<Order, String> orderRepository, GenericRepository<Customer, String> customerRepository) {
        this.customerRepository = customerRepository;
        this.receiver = new OrderReceiver(orderRepository, customerRepository);
        this.invoker = new OrderInvoker();
    }

    public void createCustomer(String customerId, String name, String email, String document) {
        Customer customer = new Customer(customerId, name, email, document);
        customerRepository.save(customer);
    }

    public void processOrder(String orderId, String customerId, BigDecimal amount) {
        Command command = new ProcessOrderCommand(receiver, orderId, customerId, amount);
        invoker.setCommand(command);
        invoker.executeCommand();
    }

    public void cancelOrder(String orderId) {
        Command command = new CancelOrderCommand(receiver, orderId);
        invoker.setCommand(command);
        invoker.executeCommand();
    }
}
