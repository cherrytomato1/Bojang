package com.ssafy.api.service;

import com.ssafy.db.entity.FavoriteStore;

import java.util.List;

public interface FavoriteService {
    FavoriteStore createFavoriteStore(String userId, String storeId);
    List<FavoriteStore> getFavoriteStore(String userId);
    List<FavoriteStore> searchFavoriteStore(String userId, String storeName);
    int deleteFavoriteStore(String favoriteStoreId);

}
