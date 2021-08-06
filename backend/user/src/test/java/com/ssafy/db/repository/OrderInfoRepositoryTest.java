package com.ssafy.db.repository;

import com.ssafy.db.entity.OrderInfo;
import com.ssafy.db.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderInfoRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderListRepository orderListRepository;


    // 사용자의 전체 주문 리스트
    @Test
    public void getOrderList() {
        User user = userRepository.findById("S99b735248a08").get();
        List<OrderInfo> list = orderListRepository.findByUser(user);
        System.out.println(list.size());
    }

    // 사용자의 주문 리스트 1개
    @Test
    public void test() {
        OrderInfo orderInfo = orderListRepository.findById("S5121721f5efd").get();
        orderInfo.setRegisterTime(LocalDateTime.of(2020, 5, 5, 3, 1));
        orderListRepository.save(orderInfo);
    }

    // 주문 리스트 저장
    @Test
    public void createOrderList() {
        User user = userRepository.findById("S99b735248a08").get();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUser(user);
        orderInfo.setPrice(30000);
        orderListRepository.save(orderInfo);
    }

    //주문 리스트 6개월
    @Test
    public void findOrderListLast6Months() {
        User user = userRepository.findById("S99b735248a08").get();
        LocalDateTime endDatetime = LocalDateTime.now();
        LocalDateTime startDatetime = endDatetime.minusMonths(6);
        List<OrderInfo> list = orderListRepository.findByRegisterTimeBetweenAndUser(startDatetime, endDatetime, user);
        System.out.println(list.size());
    }

    // 주문 리스트 n년
    @Test
    public void findOrderListLastYear() {
        User user = userRepository.findById("S99b735248a08").get();
        int year = 2020;
        LocalDateTime start = LocalDateTime.of(year, 1, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, 12, 31, 23, 59, 59);
        List<OrderInfo> list = orderListRepository.findByRegisterTimeBetweenAndUser(start, end, user);
        System.out.println(list.size());
    }

}