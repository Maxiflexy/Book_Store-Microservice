package com.maxiflexy.orderservice.web.controller;

import com.maxiflexy.orderservice.domain.models.OrderSummary;
import com.maxiflexy.orderservice.dto.OrderDTO;
import com.maxiflexy.orderservice.service.OrderService;
import com.maxiflexy.orderservice.config.SecurityService;
import com.maxiflexy.orderservice.dto.CreateOrderRequest;
import com.maxiflexy.orderservice.dto.CreateOrderResponse;
import com.maxiflexy.orderservice.web.exception.OrderNotFoundException;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
//@SecurityRequirement(name = "security_auth")
public class OrderController {

    private final OrderService orderService;
    private final SecurityService securityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        String userName = securityService.getLoginUsername();
        log.info("Creating order for user: {}", userName);
        return orderService.createOrder(userName, request);
    }

    @GetMapping
    List<OrderSummary> getOrders() {
        String userName = securityService.getLoginUsername();
        log.info("Fetching orders for user: {}", userName);
        return orderService.findOrders(userName);
    }

    @GetMapping(value = "/{orderNumber}")
    OrderDTO getOrder(@PathVariable(value = "orderNumber") String orderNumber) {
        log.info("Fetching order by id: {}", orderNumber);
        String userName = securityService.getLoginUsername();
        return orderService
                .findUserOrder(userName, orderNumber)
                .orElseThrow(() -> new OrderNotFoundException(orderNumber));
    }

}
