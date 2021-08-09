package com.ssafy.api.controller;

import com.ssafy.api.response.store.StoreGetResponse;
import com.ssafy.api.response.store.StoreListGetResponse;
import com.ssafy.api.service.store.StoreService;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    RestUtil restUtil;

    @Autowired
    StoreService storeService;

    @GetMapping()
    public ResponseEntity<?> getStore(@RequestHeader("Authorization") String token) {
        try {
            String userId = restUtil.getUserId(token);
            return ResponseEntity.ok(StoreGetResponse.of(HttpStatus.OK.value(), "Success", storeService.getStore(userId)));
        } catch (ResourceNotFoundException e) {

            return ResponseEntity.status(404).body(StoreGetResponse.of(404, "가게 정보 조회 실패", null));
        }
    }

    @GetMapping("/market/{marketId}")
    public ResponseEntity<?> getMarketStore(@PathVariable("marketId") Long marketId) {
        try {
            return ResponseEntity.ok(StoreListGetResponse.of(200, "Success", storeService.getMarketList(marketId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }


}
