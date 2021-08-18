package com.ssafy.api.service.favorite;

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
    // 사용자의 관심 가게 전체 리스트
    public List<FavoriteStore> getFavoriteStore(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            // 사용자 찾기
            User user = optionalUser.get();
            // 사용자의 관심가게 전체 리스트
            List<FavoriteStore> list = favoriteStoreRepository.findByUser(user);
            return list;
        }
        return null;
    }

    @Override
    public FavoriteStore createFavoriteStore(String userId, String storeId) {
        // 사용자 찾기
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // 가게 찾기
            Optional<Store> optionalStore = storeRepositiory.findById(storeId);
            if (optionalStore.isPresent()) {
                Store store = optionalStore.get();
                if (findFavoriteStore(store) == null) {
                    FavoriteStore favoriteStore = new FavoriteStore();
                    favoriteStore.setUser(user);
                    favoriteStore.setStore(store);
                    return favoriteStoreRepository.save(favoriteStore);
                } else
                    return null;

            }
        }
        return null;
    }

    @Override
    public FavoriteStore findFavoriteStore(Store store) {
        Optional<FavoriteStore> favoriteStoreOptional = favoriteStoreRepository.findByStore(store);
        if (favoriteStoreOptional.isPresent()) {
            return favoriteStoreOptional.get();
        }
        return null;
    }

    @Override
    public List<FavoriteStore> searchFavoriteStore(String userId, String storeName) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            // 사용자 찾기
            User user = optionalUser.get();
            // 사용자의 관심가게 전체 리스트
            return favoriteStoreRepository.findByStore_NameContainingAndUser(storeName, user);
        }
        return null;
    }

    @Override
    public int deleteFavoriteStore(String favoriteStoreId) {
        Optional<FavoriteStore> favoriteStoreOptional = favoriteStoreRepository.findById(favoriteStoreId);
        if (favoriteStoreOptional.isPresent()) {
            FavoriteStore favoriteStore = favoriteStoreOptional.get();
            favoriteStoreRepository.delete(favoriteStore);
            return 1;
        }
        return 0;
    }


}
