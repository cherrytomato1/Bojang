package com.ssafy.api.controller;


import com.ssafy.api.response.favorite.FavoriteListGetResponse;
import com.ssafy.api.response.favorite.FavoriteRegisterPostResponse;
import com.ssafy.api.response.favorite.FavoriteSearchGetResponse;
import com.ssafy.api.service.favorite.FavoriteService;
import com.ssafy.common.exception.handler.BadRequestException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.RestUtil;
import com.ssafy.db.entity.FavoriteStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> getFavoriteList(@RequestHeader("Authorization") String token) {
        try {
            String id = restUtil.getUserId(token);
            List<FavoriteStore> list = favoriteService.getFavoriteStore(id);
            return ResponseEntity.ok(FavoriteListGetResponse.of(HttpStatus.OK.value(), "Success", list));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(FavoriteListGetResponse.of(HttpStatus.NOT_FOUND.value(), "관심가게 리스트 조회 실패", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }

    // 사용자 관심 가게 등록
    @PostMapping("")
    public ResponseEntity<?> resgisterFavoriteStore(@RequestHeader("Authorization") String token, String storeId) {
        try {
            String userId = restUtil.getUserId(token);
            return ResponseEntity.ok(FavoriteRegisterPostResponse.of(201, "Success", favoriteService.createFavoriteStore(userId, storeId)));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "등록 실패"));
        } catch (BadRequestException ex) {
            return ResponseEntity.status(400)
                    .body(BaseResponseBody.of(400, ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }

    // 사용자 관심 가게 검색
    @GetMapping("/search")
    public ResponseEntity<?> searchFavoriteStore(@RequestHeader("Authorization") String token, String storeName) {
        try {
            String userId = restUtil.getUserId(token);
            List<FavoriteStore> favoriteStoreList = favoriteService.searchFavoriteStore(userId, storeName);
            return ResponseEntity.ok(FavoriteSearchGetResponse.of(HttpStatus.OK.value(), "Success", favoriteStoreList));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "검색 실패"));
        } catch (BadRequestException ex) {
            return ResponseEntity.status(400)
                    .body(BaseResponseBody.of(400, ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패"));
        }
    }

    // 사용자 관심 가게 삭제
    @DeleteMapping("/{storeId}")
    public ResponseEntity<?> deleteFavoriteStore(@RequestHeader("Authorization") String token, @PathVariable("storeId") String favoriteStoreId) {
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
