package com.ssafy.api.controller;

import com.ssafy.api.request.store.StoreCategoryGetRequest;
import com.ssafy.api.response.store.StoreGetResponse;
import com.ssafy.api.response.store.StoreListGetResponse;
import com.ssafy.api.response.store.StorePostResponse;
import com.ssafy.api.service.store.StoreService;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<?> getStoreInfo(String storeId) {
        try {
            return ResponseEntity.ok(StoreGetResponse.of(200, "Success", storeService.getStoreInfo(storeId)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(StoreGetResponse.of(404, "가게 정보 조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
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

    @PatchMapping("/commnet")
    public ResponseEntity<?> updateComment(@RequestHeader("Authorization") String token, String comment) {
        try {
            String userId = restUtil.getUserId(token);
            return ResponseEntity.ok(StorePostResponse.of(201, "Success", storeService.updateComment(userId, comment)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(StoreGetResponse.of(404, "가게 정보 조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadStoreImage(@RequestHeader("Authorization") String token, MultipartFile file) {
        try {
            String userId = restUtil.getUserId(token);
            storeService.storeImgUpload(file, userId);
            return ResponseEntity.ok(BaseResponseBody.of(201, "Success"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(StoreGetResponse.of(404, "가게 정보 조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getStoreType(StoreCategoryGetRequest request) {
        try {
            return ResponseEntity.ok(StoreListGetResponse.of(200, "Success", storeService.getStoreTypeList(request.getMarketId(), request.getStoreTypeId())));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(StoreGetResponse.of(404, "가게 정보 조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }


}
