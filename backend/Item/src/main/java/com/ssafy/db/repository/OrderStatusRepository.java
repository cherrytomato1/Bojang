package com.ssafy.db.repository;

import com.ssafy.db.entity.OrderStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderStatusRepository extends JpaRepository<OrderStatus,String> {
	Optional<OrderStatus> findById(Long id);
}
