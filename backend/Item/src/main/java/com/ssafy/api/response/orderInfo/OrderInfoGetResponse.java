package com.ssafy.api.response.orderInfo;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.OrderInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInfoGetResponse extends BaseResponseBody {
    OrderInfo orderInfo;

    public static OrderInfoGetResponse of(Integer statusCode, String message, OrderInfo orderInfo) {
        OrderInfoGetResponse response = new OrderInfoGetResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setOrderInfo(orderInfo);
        return response;
    }
}
