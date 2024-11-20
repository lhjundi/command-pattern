package com.lhjundi.ordersystem.application.command;

import com.lhjundi.ordersystem.application.receiver.OrderReceiver;

public class CancelOrderCommand implements Command {
    private final OrderReceiver receiver;
    private final String orderId;

    public CancelOrderCommand(OrderReceiver receiver, String orderId) {
        this.receiver = receiver;
        this.orderId = orderId;
    }

    @Override
    public void execute() {
        receiver.cancelOperation(orderId);
    }
}
