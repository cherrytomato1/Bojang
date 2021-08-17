package com.ssafy.api.service.orderitem;

import com.ssafy.common.model.dto.StoreOrderItemDto;
import com.ssafy.db.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<StoreOrderItemDto> getStoreOrderItem(String storeId);
    int setPickStatus(String orderItemId,boolean pickStatus);
}
