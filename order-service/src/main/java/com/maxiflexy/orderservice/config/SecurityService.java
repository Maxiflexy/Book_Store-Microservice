package com.maxiflexy.orderservice.config;

import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public String getLoginUsername(){
        return "user";
    }
}
