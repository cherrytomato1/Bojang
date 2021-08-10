package com.ssafy.db.repository;

import com.ssafy.db.entity.OrderInfo;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface OrderInfoRepository extends JpaRepository<OrderInfo,String> {
    List<OrderInfo> findByUser(User user);

    List<OrderInfo> findByRegisterTimeBetween(LocalDateTime start, LocalDateTime end);
    List<OrderInfo> findByRegisterTimeBetweenAndUser(LocalDateTime start, LocalDateTime end, User user);
    Optional<OrderInfo> findById(String id);
}
