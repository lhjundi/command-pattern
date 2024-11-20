package com.lhjundi.ordersystem.application.invoker;

import com.lhjundi.ordersystem.application.command.Command;

public final class OrderInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        if (command == null) throw new IllegalStateException("No command set");
        command.execute();
    }
}
