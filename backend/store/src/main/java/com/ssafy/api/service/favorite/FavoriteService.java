package com.ssafy.api.service.favorite;

import com.ssafy.db.entity.FavoriteStore;
import com.ssafy.db.entity.Store;

import java.util.List;

public interface FavoriteService {

    List<FavoriteStore> getFavoriteStore(String userId);
    List<FavoriteStore> searchFavoriteStore(String userId,String storeName);
    int deleteFavoriteStore(String favoriteStoreId);
    FavoriteStore createFavoriteStore(String userId, String storeId);
    FavoriteStore findFavoriteStore(Store store);
}
