package com.ssafy.api.service;

import com.ssafy.api.request.FavoriteRegisterPostReq;
import com.ssafy.db.entity.FavoriteStore;

import java.util.List;

public interface FavoriteService {
    FavoriteStore createFavoriteStore(FavoriteRegisterPostReq favoriteRegisterInfo);
    List<FavoriteStore> getFavoriteStore(String userId);
    FavoriteStore findFavoriteStore();
    void deleteFavoriteStore();

}
