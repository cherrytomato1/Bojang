package com.ssafy.api.service.orderInfo;

import com.ssafy.api.request.orderinfo.OrderInfoPatchRequest;
import com.ssafy.db.entity.OrderInfo;
import com.ssafy.db.entity.User;

import java.util.List;

public interface OrderInfoService {

    OrderInfo getOrderInfo(String orderInfoId);

    List<OrderInfo> getOrderInfoList(User user, Integer year);

    List<OrderInfo> getMarketInfoList(Long marketId);

    OrderInfo patchOrderInfoStatus(OrderInfoPatchRequest orderInfoPatchRequest);

}
