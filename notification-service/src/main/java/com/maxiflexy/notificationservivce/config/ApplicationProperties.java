package com.maxiflexy.notificationservivce.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "notifications")
public record ApplicationProperties(
        String supportEmail,
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
