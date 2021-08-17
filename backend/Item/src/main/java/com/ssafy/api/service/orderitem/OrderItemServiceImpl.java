package com.ssafy.api.service.orderitem;

import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.dto.StoreOrderItemDto;
import com.ssafy.db.entity.OrderItem;
import com.ssafy.db.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<StoreOrderItemDto> getStoreOrderItem(String storeId) {

        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);

        List<OrderItem> itemList = orderItemRepository.findByItem_Store_IdAndOrderInfo_RegisterTimeBetween(storeId, start, end);

        List<StoreOrderItemDto> list = new ArrayList<>();
        for (OrderItem item : itemList) {
            StoreOrderItemDto storeOrderItemDto = new StoreOrderItemDto();
            storeOrderItemDto.setItem(item.getItem());
            storeOrderItemDto.setOrderItemId(item.getId());
            storeOrderItemDto.setPickStatus(item.isPickStatus());
            storeOrderItemDto.setOrderInfoId(item.getOrderInfo().getId());
            storeOrderItemDto.setAmount(item.getAmount());
            storeOrderItemDto.setComment(item.getComment());
            storeOrderItemDto.setOrderStatus(item.getOrderInfo().getOrderStatus());
            list.add(storeOrderItemDto);
        }
        return list;
    }

    @Override
    public int setPickStatus(String orderItemId, boolean pickStatus) {

        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(() -> new ResourceNotFoundException("OrderItem", "Id", orderItemId));
        orderItem.setPickStatus(pickStatus);
        OrderItem save = orderItemRepository.save(orderItem);
        if(save != null){
            return 1;
        }
        return 0;
    }
}
