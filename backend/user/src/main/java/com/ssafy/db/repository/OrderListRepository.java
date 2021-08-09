package com.ssafy.db.repository;

import com.ssafy.db.entity.OrderList;
import com.ssafy.db.entity.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface OrderListRepository extends JpaRepository<OrderList,String> {
    List<OrderList> findByUser(User user);

    List<OrderList> findByRegisterTimeBetween(LocalDateTime start,LocalDateTime end);
    List<OrderList> findByRegisterTimeBetweenAndUser(LocalDateTime start,LocalDateTime end,User user);
    Optional<OrderList> findById(String id);
}
