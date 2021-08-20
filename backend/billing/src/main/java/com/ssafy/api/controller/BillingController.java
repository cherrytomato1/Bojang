package com.ssafy.api.controller;

import com.ssafy.api.service.BillingService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Billing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bill")
public class BillingController {

    @Autowired
    BillingService billingService;

    @PostMapping
    public ResponseEntity<BaseResponseBody> billing(String orderInfoId) {
        System.out.println(">>>>>>>>>>>>>>>>>" + orderInfoId);
        if (orderInfoId == null) {
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "주문 번호 오류"));
        }
        Billing billing = billingService.createBill(orderInfoId);
        if (billing == null)
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "결제 실패"));
        else
            return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
    }
}
