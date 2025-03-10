package com.maxiflexy.orderservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "orders")
public record ApplicationProperties(
        String catalogServiceUrl,
        String orderEventsExchange,
        String newOrdersQueue,
        String newOrdersRoutingKey,
        String deliveredOrdersQueue,
        String deliveredOrdersRoutingKey,
        String cancelledOrdersQueue,
        String cancelledOrdersRoutingKey,
        String errorOrdersQueue,
        String errorOrdersRoutingKey
) {}
