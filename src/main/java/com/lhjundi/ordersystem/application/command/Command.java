package com.lhjundi.ordersystem.application.command;

public sealed interface Command permits CancelOrderCommand, ProcessOrderCommand {

    void execute();

}
