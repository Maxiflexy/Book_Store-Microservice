package com.maxiflexy.orderservice.repository;

import com.maxiflexy.orderservice.domain.OrderEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEventEntity, Long> {}
