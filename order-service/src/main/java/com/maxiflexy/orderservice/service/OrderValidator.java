package com.maxiflexy.orderservice.service;

import com.maxiflexy.orderservice.client.ProductDTO;
import com.maxiflexy.orderservice.client.ProductServiceClient;
import com.maxiflexy.orderservice.domain.models.OrderItem;
import com.maxiflexy.orderservice.dto.CreateOrderRequest;
import com.maxiflexy.orderservice.web.exception.InvalidOrderException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
class OrderValidator {

    private final ProductServiceClient client;

    void validate(CreateOrderRequest request) {
        Set<OrderItem> items = request.items();
        for (OrderItem item : items) {
            ProductDTO product = client.getProductByCode(item.code())
                    .orElseThrow(() -> new InvalidOrderException("Invalid Product code:" + item.code()));
            if (item.price().compareTo(product.price()) != 0) {
                log.error(
                        "Product price not matching. Actual price:{}, received price:{}",
                        product.price(),
                        item.price());
                throw new InvalidOrderException("Product price not matching");
            }
        }
    }
}
