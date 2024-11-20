package com.lhjundi.ordersystem.application.command;

import com.lhjundi.ordersystem.application.receiver.OrderReceiver;
import com.lhjundi.ordersystem.domain.repository.OrderRepository;

import java.math.BigDecimal;

public class ProcessOrderCommand implements Command{

    private final OrderReceiver receiver;
    private final String orderId;
    private final String customerId;
    private final BigDecimal amount;

    public ProcessOrderCommand(OrderReceiver receiver, String orderId, String customerId, BigDecimal amount) {
        this.receiver = receiver;
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
    }

    @Override
    public void execute() {
        receiver.processOperation(orderId, customerId, amount);
    }
}
