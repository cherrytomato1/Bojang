package com.ssafy.db.repository;

import com.ssafy.db.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderItemRepository extends JpaRepository<OrderItem,String> {
}
