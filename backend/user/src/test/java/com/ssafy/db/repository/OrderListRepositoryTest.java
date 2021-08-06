package com.ssafy.db.repository;

import com.ssafy.db.entity.OrderList;
import com.ssafy.db.entity.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderListRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderListRepository orderListRepository;


    // 사용자의 전체 주문 리스트
    @Test
    public void getOrderList() {
        User user = userRepository.findById("S99b735248a08").get();
        List<OrderList> list = orderListRepository.findByUser(user);
        System.out.println(list.size());
    }

    // 사용자의 주문 리스트 1개
    @Test
    public void test() {
        OrderList orderList = orderListRepository.findById("S5121721f5efd").get();
        orderList.setRegisterTime(LocalDateTime.of(2020, 5, 5, 3, 1));
        orderListRepository.save(orderList);
    }

    // 주문 리스트 저장
    @Test
    public void createOrderList() {
        User user = userRepository.findById("S99b735248a08").get();
        OrderList orderList = new OrderList();
        orderList.setUser(user);
        orderList.setPrice(30000);
        orderListRepository.save(orderList);
    }

    //주문 리스트 6개월
    @Test
    public void findOrderListLast6Months() {
        User user = userRepository.findById("S99b735248a08").get();
        LocalDateTime endDatetime = LocalDateTime.now();
        LocalDateTime startDatetime = endDatetime.minusMonths(6);
        List<OrderList> list = orderListRepository.findByRegisterTimeBetweenAndUser(startDatetime, endDatetime, user);
        System.out.println(list.size());
    }

    // 주문 리스트 n년
    @Test
    public void findOrderListLastYear() {
        User user = userRepository.findById("S99b735248a08").get();
        int year = 2020;
        LocalDateTime start = LocalDateTime.of(year, 1, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, 12, 31, 23, 59, 59);
        List<OrderList> list = orderListRepository.findByRegisterTimeBetweenAndUser(start, end, user);
        System.out.println(list.size());
    }

}