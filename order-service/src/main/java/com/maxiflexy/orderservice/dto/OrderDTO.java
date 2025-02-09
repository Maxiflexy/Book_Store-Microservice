package com.maxiflexy.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxiflexy.orderservice.domain.models.Address;
import com.maxiflexy.orderservice.domain.models.Customer;
import com.maxiflexy.orderservice.domain.models.OrderItem;
import com.maxiflexy.orderservice.domain.models.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record OrderDTO(
        String orderNumber,
        String user,
        Set<OrderItem> items,
        Customer customer,
        Address deliveryAddress,
        OrderStatus status,
        String comments,
        LocalDateTime createdAt) {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public BigDecimal getTotalAmount() {
        return items.stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
