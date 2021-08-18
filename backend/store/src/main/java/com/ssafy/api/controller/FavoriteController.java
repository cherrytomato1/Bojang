package com.ssafy.api.controller;


import com.ssafy.api.request.favorite.FavoriteRegisterPostReq;
import com.ssafy.api.response.favorite.FavoriteListGetResponse;
import com.ssafy.api.response.favorite.FavoriteRegisterPostResponse;
import com.ssafy.api.response.favorite.FavoriteSearchGetResponse;
import com.ssafy.api.service.favorite.FavoriteService;
import com.ssafy.common.exception.handler.BadRequestException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.FavoriteStore;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@CrossOrigin("*")
@Api(value = "관심가게 API")
@RestController
@RequestMapping("/api/favorite")
@Slf4j
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    RestUtil restUtil;

    // 사용자의 전체 관심가게 리스트
    @GetMapping("")
    @ApiOperation(value = "사용자의 전체 관심 가게", notes = "관심 가게 리스트 반환", response = FavoriteListGetResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<FavoriteListGetResponse> getFavoriteList(@RequestHeader("Authorization") @ApiParam(value = "사용자 토큰") @ApiIgnore String token) {
        try {
            String id = restUtil.getUserId(token);
            List<FavoriteStore> list = favoriteService.getFavoriteStore(id);
            return ResponseEntity.ok(FavoriteListGetResponse.of(HttpStatus.OK.value(), "Success", list));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(FavoriteListGetResponse.of(HttpStatus.NOT_FOUND.value(), "관심가게 리스트 조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(FavoriteListGetResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패",null));
        }
    }

    // 사용자 관심 가게 등록
    @PostMapping("")
    @ApiOperation(value = "사용자의 관심 가게 등록", notes = "관심 가게 객체 반환", response = FavoriteRegisterPostResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<FavoriteRegisterPostResponse> registerFavoriteStore(@RequestHeader("Authorization") @ApiParam(value = "사용자 토큰") @ApiIgnore String token, @RequestBody @ApiParam(value = "가게 ID") FavoriteRegisterPostReq favoriteRegisterPostReq) {
        try {
            String userId = restUtil.getUserId(token);
            return ResponseEntity.ok(FavoriteRegisterPostResponse.of(201, "Success", favoriteService.createFavoriteStore(userId, favoriteRegisterPostReq.getStoreId())));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(FavoriteRegisterPostResponse.of(404, "등록 실패",null));
        } catch (BadRequestException ex) {
            return ResponseEntity.status(400)
                    .body(FavoriteRegisterPostResponse.of(400, ex.getMessage(),null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(FavoriteRegisterPostResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패",null));
        }
    }

    // 사용자 관심 가게 검색
    @GetMapping("/search")
    @ApiOperation(value = "사용자의 관심 가게 검색", notes = "관심 가게 리스트 반환", response = FavoriteSearchGetResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<FavoriteSearchGetResponse> searchFavoriteStore(@RequestHeader("Authorization") @ApiParam(value = "사용자 토큰") @ApiIgnore String token, @ApiParam(value = "관심 가게 이름", example = "싸피 가게") String storeName) {
        try {
            String userId = restUtil.getUserId(token);
            List<FavoriteStore> favoriteStoreList = favoriteService.searchFavoriteStore(userId, storeName);
            return ResponseEntity.ok(FavoriteSearchGetResponse.of(HttpStatus.OK.value(), "Success", favoriteStoreList));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(FavoriteSearchGetResponse.of(404, "검색 실패",null));
        } catch (BadRequestException ex) {
            return ResponseEntity.status(400)
                    .body(FavoriteSearchGetResponse.of(400, ex.getMessage(),null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(FavoriteSearchGetResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패",null));
        }
    }

    // 사용자 관심 가게 삭제
    @DeleteMapping("/{storeId}")
    @ApiOperation(value = "사용자의 관심 가게 삭제", response = BaseResponseBody.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<BaseResponseBody> deleteFavoriteStore(@RequestHeader("Authorization") @ApiParam(value = "사용자 토큰") @ApiIgnore String token, @PathVariable("storeId") @ApiParam(value = "관심 가게 ID") String favoriteStoreId) {
        try {
            String userId = restUtil.getUserId(token);
            int res = favoriteService.deleteFavoriteStore(favoriteStoreId);
            if (res > 0)
                return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK.value(), "Succes"));
            else
                return ResponseEntity.status(500).body(BaseResponseBody.of(500, "삭제 실패"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "삭제 실패"));
        } catch (BadRequestException ex) {
            return ResponseEntity.status(400)
                    .body(BaseResponseBody.of(400, ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }


}
