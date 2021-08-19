package com.ssafy.api.controller;


import com.ssafy.api.request.orderinfo.PickStatusPostRequest;
import com.ssafy.api.response.basket.BasketPutResponse;
import com.ssafy.api.response.orderitem.StoreOrderItemListGetResponse;
import com.ssafy.api.service.orderitem.OrderItemService;
import com.ssafy.common.model.dto.StoreOrderItemDto;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orderitem")
@Api(value = "주문 품목")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @ApiOperation(value = "해당 스토어 ID 당일 주문 목록", notes = "해당일 0시~24시 사이 주문 목록", response =
            StoreOrderItemListGetResponse.class)
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreOrderItemListGetResponse> getStoreOrderItem(@PathVariable String storeId) {
        try {
            List<StoreOrderItemDto> list = orderItemService.getStoreOrderItem(storeId);

            return ResponseEntity.ok(StoreOrderItemListGetResponse.of(200, "Success", list));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(StoreOrderItemListGetResponse.of(500, "조회 실패", null));
        }
    }

    @ApiOperation(value = "해당 orderItem ID 픽업 상태 변경", notes = "true or false 로 변경", response =
            BaseResponseBody.class)
    @PostMapping()
    public ResponseEntity<BaseResponseBody> setPickStatus(@RequestBody PickStatusPostRequest request) {
        try {
            if (orderItemService.setPickStatus(request.getOrderItemId(), request.isPickStatus()) == 1) {
                return ResponseEntity.ok(BaseResponseBody.of(201, "Success"));
            } else {
                return ResponseEntity.ok(BaseResponseBody.of(404, "Failed"));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(BaseResponseBody.of(500, "조회 실패"));
        }
    }


}
