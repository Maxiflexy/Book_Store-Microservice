package com.maxiflexy.orderservice.dto;

import java.util.Set;

import com.maxiflexy.orderservice.domain.models.Address;
import com.maxiflexy.orderservice.domain.models.Customer;
import com.maxiflexy.orderservice.domain.models.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record CreateOrderRequest (

        @Valid
        @NotEmpty(message = "Items cannot be empty")
        Set<OrderItem> items,

        @Valid
        Customer customer,

        @Valid
        Address deliveryAddress
){}
