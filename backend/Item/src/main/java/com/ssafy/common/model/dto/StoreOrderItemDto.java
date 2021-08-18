package com.ssafy.common.model.dto;

import com.ssafy.db.entity.Item;
import com.ssafy.db.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreOrderItemDto {

    String orderInfoId;

    String orderItemId;

    OrderStatus orderStatus;

    Item item;

    boolean pickStatus;

    Long amount;

    String comment;

}
