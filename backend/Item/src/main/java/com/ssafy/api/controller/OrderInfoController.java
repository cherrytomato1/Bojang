package com.ssafy.api.controller;

import com.ssafy.api.response.orderInfo.OrderInfoGetResponse;
import com.ssafy.api.response.orderInfo.OrderInfoListGetResponse;
import com.ssafy.api.service.orderInfo.OrderInfoService;
import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.exception.handler.RestTemplateException;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.OrderInfo;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orderinfo")
@Api(value = "주문 내역확인")
public class OrderInfoController {

    @Autowired
    RestUtil restUtil;

    @Autowired
    OrderInfoService orderInfoService;

    @GetMapping("")
    @ApiOperation(value = "사용자의 주문내역 list 조회", notes = "year 없으면 6개월, 파라메터값 넣으면 해당 년도 주문내역 list 반환", response = OrderInfoListGetResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<OrderInfoListGetResponse> getOrderInfoList(@ApiIgnore @RequestHeader("Authorization") String token, @ApiParam(value = "조회 년도,없으면 6개월", example = "2021") Integer year) {
        try {
            User user = restUtil.getUserByToken(token);
            List<OrderInfo> list = orderInfoService.getOrderInfoList(user, year);
            return ResponseEntity.ok(OrderInfoListGetResponse.of(200, "Success", list));
        } catch (AuthException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(OrderInfoListGetResponse.of(401, ex.getMessage(), null));
        } catch (ResourceNotFoundException | RestTemplateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(OrderInfoListGetResponse.of(404, ex.getMessage(), null));
        }
    }

    @GetMapping("/{orderInfoId}")
    @ApiOperation(value = "사용자의 주문내역 조회", notes = "해당 주문 ID의 주문 내역 조회", response = OrderInfoListGetResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<OrderInfoGetResponse> getOrderInfo(@ApiIgnore @RequestHeader("Authorization") String token, @PathVariable @ApiParam(value = "주문내역 ID") String orderInfoId) {
        OrderInfo orderInfo = null;
        try {
            User user = restUtil.getUserByToken(token);
            if (user != null)
                orderInfo = orderInfoService.getOrderInfo(orderInfoId);
            return ResponseEntity.ok(OrderInfoGetResponse.of(200, "Success", orderInfo));
        } catch (AuthException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(OrderInfoGetResponse.of(401, ex.getMessage(), null));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(OrderInfoGetResponse.of(404, "주문내역 조회 실패", null));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(OrderInfoGetResponse.of(400, "주문내역 조회 실패", null));
        }
    }

    @GetMapping("/market")
    @ApiOperation(value = "픽업 매니저의 주문내역 조회", notes = "해당 픽업매니저의 시장 주문 내역 조회", response = OrderInfoListGetResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<OrderInfoListGetResponse> getMarketOrderInfo(@ApiIgnore @RequestHeader("Authorization") String token) {
        List<OrderInfo> orderInfoList = null;
        try {
            User user = restUtil.getUserByToken(token);
            if (user.getUserType().getId() == 3L) {
                orderInfoList = orderInfoService.getMarketInfoList(user.getMarket().getId());
                return ResponseEntity.ok(OrderInfoListGetResponse.of(200, "픽업 리스트", orderInfoList));
            } else
                return ResponseEntity.status(400).body(OrderInfoListGetResponse.of(400, "픽업 매니저가 아닙니다!", null));
        } catch (AuthException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(OrderInfoListGetResponse.of(401, ex.getMessage(), null));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(OrderInfoListGetResponse.of(404, "주문내역 조회 실패", null));
        }
    }
}
