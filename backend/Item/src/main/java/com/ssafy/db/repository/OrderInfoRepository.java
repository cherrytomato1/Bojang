package com.ssafy.db.repository;

import com.ssafy.db.entity.OrderInfo;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface OrderInfoRepository extends JpaRepository<OrderInfo, String> {

	List<OrderInfo> findByUser(User user);

	List<OrderInfo> findByRegisterTimeBetweenAndUserOrderByRegisterTimeDesc(LocalDateTime start, LocalDateTime end,
																			User user);

	Optional<OrderInfo> findById(String id);

//	List<OrderInfo> findByMarket_IdAndOrderStatus_Id(Long marketId, Long orderStatusId);

	List<OrderInfo> findByMarket_IdAndOrderStatus_IdNot(Long marketId, Long orderStatusId);

}
