package com.ssafy.db.repository;

import com.ssafy.common.model.dto.StoreOrderItemDto;
import com.ssafy.db.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderItemRepositoryTest {

    @Autowired
    OrderItemRepository orderItemRepository;
    @Test
    void findByItem_Store_Id() {
        List<OrderItem> itemList = orderItemRepository.findByItem_Store_Id("Sf7b9b55e5f3f");
        List<StoreOrderItemDto> list = new ArrayList<>();
        for(OrderItem item : itemList){
            StoreOrderItemDto storeOrderItemDto = new StoreOrderItemDto();
            storeOrderItemDto.setItem(item.getItem());
            storeOrderItemDto.setOrderInfoId(item.getOrderInfo().getId());
            storeOrderItemDto.setAmount(item.getAmount());
            storeOrderItemDto.setComment(item.getComment());
            storeOrderItemDto.setOrderStatus(item.getOrderInfo().getOrderStatus());
            list.add(storeOrderItemDto);
        }
    }
}