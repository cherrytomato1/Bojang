package com.ssafy.api.controller;

import com.ssafy.api.request.store.StoreCategoryGetRequest;
import com.ssafy.api.response.store.StoreGetResponse;
import com.ssafy.api.response.store.StoreListGetResponse;
import com.ssafy.api.response.store.StorePostResponse;
import com.ssafy.api.service.store.StoreService;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.RestUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin("*")
@Api(value = "가게 CRUD API")
@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    RestUtil restUtil;

    @Autowired
    StoreService storeService;

    @GetMapping()
    @ApiOperation(value = "판매자의 해당 가게 정보", notes = "store 객체 반환", response = StoreGetResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<StoreGetResponse> getStore(@RequestHeader("Authorization") @ApiIgnore String token) {
        try {
            String userId = restUtil.getUserId(token);
            return ResponseEntity.ok(StoreGetResponse.of(HttpStatus.OK.value(), "Success", storeService.getStore(userId)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(StoreGetResponse.of(404, "가게 정보 조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(StoreGetResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패",null));
        }
    }

    @GetMapping("/{storeId}")
    @ApiOperation(value = "해당 ID의 가게 정보", notes = "store 객체 반환", response = StoreGetResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<StoreGetResponse> getStoreInfo(@ApiParam(value = "가게 ID") String storeId) {
        try {
            return ResponseEntity.ok(StoreGetResponse.of(200, "Success", storeService.getStoreInfo(storeId)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(StoreGetResponse.of(404, "가게 정보 조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(StoreGetResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패",null));
        }
    }

    @GetMapping("/market/{marketId}")
    @ApiOperation(value = "해당 시장의 가게 리스트", notes = "store 리스트 반환", response = StoreListGetResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<StoreListGetResponse> getMarketStore(@PathVariable("marketId") @ApiParam(value = "마켓 ID") Long marketId) {
        try {
            return ResponseEntity.ok(StoreListGetResponse.of(200, "Success", storeService.getMarketList(marketId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(StoreListGetResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패",null));
        }
    }

    @PatchMapping("/commnet")
    @ApiOperation(value = "판매자의 가게 정보 UPDATE", notes = "store 객체 반환", response = StorePostResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<StorePostResponse> updateComment(@RequestHeader("Authorization") @ApiIgnore String token, @ApiParam(value = "가게 설명") String comment) {
        try {
            String userId = restUtil.getUserId(token);
            return ResponseEntity.ok(StorePostResponse.of(201, "Success", storeService.updateComment(userId, comment)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(StorePostResponse.of(404, "가게 정보 조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(StorePostResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패",null));
        }
    }

    @PostMapping("/image")
    @ApiOperation(value = "판매자의 가게 이미지 upload", notes = "성공 여부 반환", response = BaseResponseBody.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<BaseResponseBody> uploadStoreImage(@RequestHeader("Authorization") @ApiIgnore String token, @ApiParam(value = "이미지 파일") MultipartFile file) {
        try {
            String userId = restUtil.getUserId(token);
            storeService.storeImgUpload(file, userId);
            return ResponseEntity.ok(BaseResponseBody.of(201, "Success"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "가게 정보 조회 실패"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }

    @GetMapping("/category")
    @ApiOperation(value = "해당 마켓의 카테고리별 가게 리스트", notes = "store 리스트 반환", response = StoreListGetResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<StoreListGetResponse> getStoreType(@ApiParam(value = "시장 ID, 카테고리 ID") StoreCategoryGetRequest request) {
        try {
            return ResponseEntity.ok(StoreListGetResponse.of(200, "Success", storeService.getStoreTypeList(request.getMarketId(), request.getStoreTypeId())));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(StoreListGetResponse.of(404, "가게 정보 조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(StoreListGetResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패",null));
        }
    }


}
