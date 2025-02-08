package com.maxiflexy.catalogservice.web.exception;


public class ProductNotFoundException extends RuntimeException{

    ProductNotFoundException(String message){
        super(message);
    }

    public static ProductNotFoundException forCode(String code){
        return new ProductNotFoundException(String.format("Product with code %s, not found", code));
    }
}
