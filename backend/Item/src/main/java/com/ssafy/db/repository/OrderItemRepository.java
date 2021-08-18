package com.ssafy.db.repository;

import com.ssafy.db.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,String> {

    List<OrderItem> findByItem_Store_Id(String storeId);
    List<OrderItem> findByItem_Store_IdAndOrderInfo_RegisterTimeBetween(String storeId, LocalDateTime start,LocalDateTime end);

}
