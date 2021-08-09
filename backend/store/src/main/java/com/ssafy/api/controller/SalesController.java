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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    RestUtil restUtil;

    @Autowired
    SalesService salesService;

    @GetMapping()
    public ResponseEntity<?> getSales(@RequestHeader("Authorization") String token) {
        try {
            String userId = restUtil.getUserId(token);
            return ResponseEntity.ok(SalesGetListResponse.of(200, "Success", salesService.getSales(userId)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(StoreGetResponse.of(404, "조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }

    @GetMapping("/date")
    public ResponseEntity<?> getDateSales(@RequestHeader("Authorization") String token, SalesGetRequest request) {
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
    public ResponseEntity<?> updateSales(@RequestHeader("Authorization") String token, Integer amount) {
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
