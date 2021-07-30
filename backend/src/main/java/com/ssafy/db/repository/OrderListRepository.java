package com.ssafy.db.repository;

import com.ssafy.db.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderListRepository extends JpaRepository<OrderList,String> {
}
