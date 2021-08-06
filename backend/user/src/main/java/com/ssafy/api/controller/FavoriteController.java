package com.ssafy.api.controller;

import com.ssafy.api.service.FavoriteService;
import com.ssafy.db.entity.FavoriteStore;
import com.ssafy.db.entity.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api(value = "유저 API", tags = {"User"})
//@RestController
//@RequestMapping("/api/v1/user/fav")
public class FavoriteController {

//    @Autowired
//    FavoriteService favoriteService;
//
//
//    // 사용자의 전체 관심가게 리스트
//    @GetMapping("/")
//    public ResponseEntity<?> getFavoriteList(@RequestHeader HttpHeaders headers){
//
//        //headers에서 클라이언트의 토큰을 auth 서버에 보내 인증을 받고 id값을 찾아낸다.
//        String userid = "ex";
//
//        List<FavoriteStore> list = favoriteService.getFavoriteStore(userid);
//
//        return ResponseEntity.ok().body(list);
//    }


}
