package com.ssafy.api.response.orderInfo;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.OrderInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderInfoListGetResponse extends BaseResponseBody {
    List<OrderInfo> list;

    public static OrderInfoListGetResponse of(Integer statusCode, String message, List<OrderInfo> list) {
        OrderInfoListGetResponse response = new OrderInfoListGetResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setList(list);
        return response;
    }
}
