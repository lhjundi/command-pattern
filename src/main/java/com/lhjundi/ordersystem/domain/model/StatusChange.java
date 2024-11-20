package com.lhjundi.ordersystem.domain.model;

import java.time.LocalDateTime;

public record StatusChange(
        OrderStatus status,
        LocalDateTime changedAt,
        String reason
) {
}
