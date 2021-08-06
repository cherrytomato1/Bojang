package com.ssafy.api.service;

import com.ssafy.api.request.FavoriteRegisterPostReq;
import com.ssafy.db.entity.FavoriteStore;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.FavoriteStoreRepository;
import com.ssafy.db.repository.StoreRepositiory;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FavoriteServiceImpl implements FavoriteService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepositiory storeRepositiory;

    @Autowired
    FavoriteStoreRepository favoriteStoreRepository;

    @Override
    // 관심 가게 등록
    public FavoriteStore createFavoriteStore(FavoriteRegisterPostReq favoriteRegisterInfo) {
        return null;
    }

    @Override
    // 사용자의 관심 가게 전체 리스트
    public List<FavoriteStore> getFavoriteStore(String userId) {

        // 사용자 찾기
        User user = userRepository.findById(userId).get();
        // 사용자의 관심가게 전체 리스트
        List<FavoriteStore> list = favoriteStoreRepository.findByUser(user);

        return list;
    }


    @Override
    // 사용자의 관심 가게 검색
    public FavoriteStore findFavoriteStore() {
        return null;
    }

    @Override
    // 사용자의 관심 가게 삭제
    public void deleteFavoriteStore() {
    }
}
