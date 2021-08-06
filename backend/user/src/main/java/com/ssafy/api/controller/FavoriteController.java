package com.ssafy.api.controller;

import com.ssafy.api.request.FavoirteStoreDeleteRequest;
import com.ssafy.api.request.FavoriteRegisterPostRequest;
import com.ssafy.api.service.FavoriteService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.GetUserIdUtil;
import com.ssafy.db.entity.FavoriteStore;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "유저 관심 가게 API")
@RestController
@RequestMapping("/api/v1/user/fav")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    GetUserIdUtil getUserIdUtil;



    // 사용자의 전체 관심가게 리스트
    @GetMapping("")
    public ResponseEntity<?> getFavoriteList(@RequestHeader HttpHeaders headers){

        //headers에서 클라이언트의 토큰을 auth 서버에 보내 인증을 받고 id값을 찾아낸다.
        String userId = getUserIdUtil.getUserId(headers);
        List<FavoriteStore> list = favoriteService.getFavoriteStore(userId);

        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/search/{storename}")
    public ResponseEntity<?> searchFavoriteList(@RequestHeader HttpHeaders headers,@PathVariable String storename){

        String userId = getUserIdUtil.getUserId(headers);
        List<FavoriteStore> list = favoriteService.searchFavoriteStore(userId,storename);


    }

    @PostMapping("")
    public ResponseEntity<?> registerFavoriteStore(@RequestHeader HttpHeaders headers, FavoriteRegisterPostRequest request){
        String userId = getUserIdUtil.getUserId(headers);
        String storeId = request.getStoreId();
        FavoriteStore favoriteStore = favoriteService.createFavoriteStore(userId,storeId);



    }

    @DeleteMapping("/{storeid}")
    public ResponseEntity<?> deleteFavoriteStore(@RequestHeader HttpHeaders headers, @PathVariable FavoirteStoreDeleteRequest request){
        String userId = getUserIdUtil.getUserId(headers);
        String storeId = request.getStoreId();
        if(favoriteService.deleteFavoriteStore(storeId) > 0){
            return ResponseEntity.ok().body(BaseResponseBody.of(HttpStatus.OK.value(),"삭제 완료"));
        }else{
            return ResponseEntity.badRequest().body(BaseResponseBody.of(HttpStatus.BAD_REQUEST.value(), "삭제 실패"));
        }
    }






}
