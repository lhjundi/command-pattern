package com.lhjundi.ordersystem.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Order {
    private final String id;
    private final Customer customer;
    private final LocalDateTime createdAt;
    private final List<StatusChange> statusHistory;
    private final BigDecimal amount;
    private OrderStatus status;
    private LocalDateTime lastModifiedAt;

    public Order(String id, Customer customer, BigDecimal amount) {
        validateId(id);
        validateCustomer(customer);
        validateAmount(amount);

        this.id = id;
        this.customer = customer;
        this.amount = amount;
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = this.createdAt;
        this.statusHistory = new ArrayList<>();
        addStatusChange("Order created");
    }

    private void validateId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be empty");
        }
    }

    private void validateCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }

    public void process() {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Order must be in CREATED state to process");
        }
        this.status = OrderStatus.PROCESSING;
        addStatusChange("Order processing started");
    }

    public void complete() {
        if (status != OrderStatus.PROCESSING) {
            throw new IllegalStateException("Order must be in PROCESSING state to complete");
        }
        this.status = OrderStatus.COMPLETED;
        addStatusChange("Order completed");
    }

    public void cancel() {
        if (status == OrderStatus.COMPLETED || status == OrderStatus.CANCELLED) {
            throw new IllegalStateException(STR."Cannot cancel order in \{status} state");
        }
        this.status = OrderStatus.CANCELLED;
        addStatusChange("Order cancelled");
    }

    private void addStatusChange(String reason) {
        this.lastModifiedAt = LocalDateTime.now();
        this.statusHistory.add(new StatusChange(status, lastModifiedAt, reason));
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public List<StatusChange> getStatusHistory() {
        return new ArrayList<>(statusHistory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
