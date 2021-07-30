package com.ssafy.db.repository;

import com.ssafy.db.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderStatusRepository extends JpaRepository<OrderStatus,String> {
}
