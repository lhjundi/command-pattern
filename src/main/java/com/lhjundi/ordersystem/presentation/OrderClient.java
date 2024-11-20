package com.lhjundi.ordersystem.presentation;

import com.lhjundi.ordersystem.application.command.CancelOrderCommand;
import com.lhjundi.ordersystem.application.command.Command;
import com.lhjundi.ordersystem.application.command.ProcessOrderCommand;
import com.lhjundi.ordersystem.application.invoker.OrderInvoker;
import com.lhjundi.ordersystem.application.receiver.OrderReceiver;
import com.lhjundi.ordersystem.domain.repository.order.OrderRepository;

import java.math.BigDecimal;

public class OrderClient {
    private final OrderReceiver receiver;
    private final OrderInvoker invoker;

    public OrderClient(OrderRepository repository) {
        this.receiver = new OrderReceiver(repository);
        this.invoker = new OrderInvoker();
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
