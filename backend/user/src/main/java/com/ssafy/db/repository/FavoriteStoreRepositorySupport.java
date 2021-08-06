package com.ssafy.db.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteStoreRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QStore qStore = QStore.store;
    QFavoriteStore qFavoriteStore = QFavoriteStore.favoriteStore;

    public List<FavoriteStore> searchByStoreName(User user, String storeName){
        return jpaQueryFactory.selectFrom(qFavoriteStore).join(qFavoriteStore.store, qStore).on(qStore.name.like(storeName)).where(qFavoriteStore.user.eq(user)).fetch();
    }

    public List<FavoriteStore> searchByUser(User user){
        List<FavoriteStore> list = null;
        list = jpaQueryFactory.select(qFavoriteStore).from(qFavoriteStore).where(qFavoriteStore.user.eq(user)).fetch();
        return list;
    }

}

