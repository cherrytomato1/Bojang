package com.ssafy.api.response.orderitem;

import com.ssafy.common.model.dto.StoreOrderItemDto;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.OrderInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreOrderItemListGetResponse extends BaseResponseBody {
    List<StoreOrderItemDto> list;

    public static StoreOrderItemListGetResponse of(Integer statusCode, String message, List<StoreOrderItemDto> list){
        StoreOrderItemListGetResponse response = new StoreOrderItemListGetResponse();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setList(list);
        return response;
    }
}
