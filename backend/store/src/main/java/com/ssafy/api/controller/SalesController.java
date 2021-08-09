package com.ssafy.api.controller;

import com.ssafy.api.request.chart.SalesGetRequest;
import com.ssafy.api.response.sales.SalesGetListResponse;
import com.ssafy.api.response.sales.SalesGetResponse;
import com.ssafy.api.response.store.StoreGetResponse;
import com.ssafy.api.service.sales.SalesService;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.Sales;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDate;

@CrossOrigin("*")
@Api(value = "가게 판매 내역 CRUD API")
@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    RestUtil restUtil;

    @Autowired
    SalesService salesService;

    @GetMapping()
    @ApiOperation(value = "판매자의 전체 판매 내역", notes = "sales 리스트 반환", response =
            SalesGetListResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<SalesGetListResponse> getSales(@RequestHeader("Authorization") @ApiIgnore String token) {
        try {
            String userId = restUtil.getUserId(token);
            return ResponseEntity.ok(SalesGetListResponse.of(200, "Success", salesService.getSales(userId)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(SalesGetListResponse.of(404, "조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(SalesGetListResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패",null));
        }
    }

    @GetMapping("/date")
    @ApiOperation(value = "판매자의 날짜별 판매 내역", notes = "start만 보내면 해당 요일, end 까지 보내면 start부터 end 사이의 sales 리스트 반환", response =
            SalesGetListResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<?> getDateSales(@RequestHeader("Authorization") @ApiIgnore String token, @ApiParam(value = "날자 정보") SalesGetRequest request) {
        try {
            String userId = restUtil.getUserId(token);
            if (request.getEnd() == null) {
                Sales sales = salesService.getDateSales(userId, request.getStart());
                return ResponseEntity.ok(SalesGetResponse.of(200, "Success", sales.getRegisterTime(), sales.getAmount()));
            } else {
                return ResponseEntity.ok(SalesGetListResponse.of(200, "Success", salesService.getDateSales(userId, request.getStart(), request.getEnd())));
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(StoreGetResponse.of(404, "조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }

    @PostMapping()
    @ApiOperation(value = "판매자의 판매 금액 UPDATE", notes = "성공 여부 반환", response =
            BaseResponseBody.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<BaseResponseBody> updateSales(@RequestHeader("Authorization") @ApiIgnore String token,@RequestBody @ApiParam(value = "판매 금액",example = "3000") Integer amount) {
        try {
            String userId = restUtil.getUserId(token);
            salesService.updateSales(userId, LocalDate.now(), amount);
            return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(StoreGetResponse.of(404, "조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }
}
