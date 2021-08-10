package com.ssafy.db.repository;

import com.ssafy.db.entity.OrderInfo;
import com.ssafy.db.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderInfoRepositoryTest {
    @Autowired
    OrderInfoRepository orderInfoRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void create() {
        OrderInfo orderInfo = new OrderInfo();
        User user = userRepository.findById("S46fd7ccf483e").get();
        orderInfo.setUser(user);
        orderInfo.setPrice(10000);
        orderInfoRepository.save(orderInfo);

    }
}