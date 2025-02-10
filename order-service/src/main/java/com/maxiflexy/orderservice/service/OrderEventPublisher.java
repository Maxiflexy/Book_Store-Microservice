package com.maxiflexy.orderservice.service;

import com.maxiflexy.orderservice.config.ApplicationProperties;
import com.maxiflexy.orderservice.domain.models.OrderCancelledEvent;
import com.maxiflexy.orderservice.domain.models.OrderCreatedEvent;
import com.maxiflexy.orderservice.domain.models.OrderDeliveredEvent;
import com.maxiflexy.orderservice.domain.models.OrderErrorEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties properties;


    public void publish(OrderCreatedEvent event) {
        this.send(properties.newOrdersRoutingKey(), event);
    }

    public void publish(OrderDeliveredEvent event) {
        this.send(properties.deliveredOrdersRoutingKey(), event);
    }

    public void publish(OrderCancelledEvent event) {
        this.send(properties.cancelledOrdersRoutingKey(), event);
    }

    public void publish(OrderErrorEvent event) {
        this.send(properties.errorOrdersRoutingKey(), event);
    }

    private void send(String routingKey, Object payload) {
        rabbitTemplate.convertAndSend(
                properties.orderEventsExchange(), // exchange name
                routingKey, // routing key
                payload
        );
    }
}
