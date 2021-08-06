package com.ssafy.api.service;

import com.ssafy.db.entity.FavoriteStore;
import com.ssafy.db.entity.Store;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.FavoriteStoreRepository;
import com.ssafy.db.repository.StoreRepositiory;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepositiory storeRepositiory;

    @Autowired
    FavoriteStoreRepository favoriteStoreRepository;

    @Override
    // 관심 가게 등록
    public FavoriteStore createFavoriteStore(String userId, String storeId) {
        // 사용자 찾기
        Optional<User> oUser = userRepository.findById(userId);
        if (oUser.isPresent()) {
            User user = oUser.get();
            Optional<Store> optionalStore = storeRepositiory.findById(storeId);
            if (optionalStore.isPresent()) {
                Store store = optionalStore.get();
                FavoriteStore favoriteStore = new FavoriteStore();
                favoriteStore.setUser(user);
                favoriteStore.setStore(store);
                favoriteStoreRepository.save(favoriteStore);
                return favoriteStore;
            }
        }
        return null;
    }

    @Override
    // 사용자의 관심 가게 전체 리스트
    public List<FavoriteStore> getFavoriteStore(String userId) {
        // 사용자 찾기
        Optional<User> oUser = userRepository.findById(userId);
        if (oUser.isPresent()) {
            User user = oUser.get();
            // 사용자의 관심가게중 검색 내용
            List<FavoriteStore> list = favoriteStoreRepository.findByUser(user);
            return list;
        }
        return null;
    }


    @Override
    public List<FavoriteStore> searchFavoriteStore(String userId, String storeName) {
        // 사용자 찾기
        Optional<User> oUser = userRepository.findById(userId);
        if (oUser.isPresent()) {
            User user = oUser.get();
            // 사용자의 관심가게중 검색 내용
            List<FavoriteStore> list = favoriteStoreRepository.findByStore_NameContainingAndUser(storeName, user);
            return list;
        } else {
            return null;
        }
    }


    @Override
    // 사용자의 관심 가게 삭제
    public int deleteFavoriteStore(String storeId) {
        Optional<FavoriteStore> optionalFavoriteStore = favoriteStoreRepository.findById(storeId);
        if (optionalFavoriteStore.isPresent()) {
            FavoriteStore favoriteStore = optionalFavoriteStore.get();
            favoriteStoreRepository.delete(favoriteStore);
            return 1;
        }
        return 0;
    }
}
