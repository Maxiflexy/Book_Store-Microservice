package com.maxiflexy.orderservice.web.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public static OrderNotFoundException forOrderNumber(String orderNumber) {
        return new OrderNotFoundException(String.format("Order with Number: %s, not found", orderNumber));
    }
}
