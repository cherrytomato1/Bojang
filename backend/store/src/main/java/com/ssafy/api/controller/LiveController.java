package com.ssafy.api.controller;

import com.ssafy.api.response.live.StoreLiveGetResponse;
import com.ssafy.api.response.live.StoreLiveListGetResponse;
import com.ssafy.api.service.live.LiveService;
import com.ssafy.api.service.store.StoreService;
import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.model.response.dto.StoreLiveDto;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.StoreLive;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/live")
public class LiveController {

    @Autowired
    LiveService liveService;

    @Autowired
    RestUtil restUtil;

    @Autowired
    StoreService storeService;

    @GetMapping()
    @ApiOperation(value = "전체 라이브 방송 리스트", response =
            StoreLiveListGetResponse.class)
    public ResponseEntity<StoreLiveListGetResponse> getLiveList() {
        try {
            return ResponseEntity.ok(StoreLiveListGetResponse.of(200, "Suceess", liveService.getLiveList()));
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(StoreLiveListGetResponse.of(404, exception.getMessage(), null));
        }
    }

    @GetMapping("/{storeId}")
    @ApiOperation(value = "해당 store Id 라이브 여부", notes = "해당 Store Id,Name 반환", response =
            StoreLiveGetResponse.class)
    public ResponseEntity<StoreLiveGetResponse> getLiveStore(@PathVariable String storeId) {
        try {
            StoreLive storeLive = liveService.getStoreLiveByStoreId(storeId);
            StoreLiveDto storeLiveDto = new StoreLiveDto();
            storeLiveDto.setStoreId(storeLive.getStore().getId());
            storeLiveDto.setStoreName(storeLive.getStore().getName());
            return ResponseEntity.ok(StoreLiveGetResponse.of(200, "Success", storeLiveDto));
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(StoreLiveGetResponse.of(404, exception.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(StoreLiveGetResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패", null));
        }
    }

    @PostMapping()
    @ApiOperation(value = "해당 사용자 라이브 방송 개설", response =
            BaseResponseBody.class)
    public ResponseEntity<BaseResponseBody> createLiveStore(@ApiIgnore @RequestHeader("Authorization") String token) {
        try {
            String userId = restUtil.getUserId(token);
            String storeId = storeService.getStoreId(userId);
            if (storeId != null) {
                StoreLive storeLive = liveService.createStoreLive(storeId);
                System.out.println("################"+ storeLive.getId());
                if (storeLive != null)
                    return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
                else
                    return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Failed"));
            } else {
                return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Failed"));
            }
        } catch (AuthException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(BaseResponseBody.of(401, ex.getMessage()));
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(BaseResponseBody.of(404, exception.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }

    @DeleteMapping()
    @ApiOperation(value = "해당 사용자 라이브 방송 삭제", response =
            BaseResponseBody.class)
    public ResponseEntity<BaseResponseBody> deleteLiveStore(@ApiIgnore @RequestHeader("Authorization") String token) {
        try {
            String userId = restUtil.getUserId(token);
            String storeId = storeService.getStoreId(userId);
            if (storeId != null) {
                liveService.deleteStoreLive(storeId);
                return ResponseEntity.ok(BaseResponseBody.of(200, "Success"));
            } else {
                return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Failed"));
            }
        } catch (AuthException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(BaseResponseBody.of(401, ex.getMessage()));
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(BaseResponseBody.of(404, exception.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }


}
