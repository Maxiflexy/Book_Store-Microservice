package com.maxiflexy.orderservice.domain.models;

public record OrderSummary(
        String orderNumber,
        OrderStatus status
) {}
