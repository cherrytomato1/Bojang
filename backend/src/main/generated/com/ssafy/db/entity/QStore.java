package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = 471205471L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore store = new QStore("store");

    public final StringPath address = createString("address");

    public final StringPath comment = createString("comment");

    public final StringPath id = createString("id");

    public final StringPath image = createString("image");

    public final QMarket marketId;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> section = createNumber("section", Integer.class);

    public final QStoreType storeTypeId;

    public final QUser user;

    public QStore(String variable) {
        this(Store.class, forVariable(variable), INITS);
    }

    public QStore(Path<? extends Store> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStore(PathMetadata metadata, PathInits inits) {
        this(Store.class, metadata, inits);
    }

    public QStore(Class<? extends Store> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.marketId = inits.isInitialized("marketId") ? new QMarket(forProperty("marketId")) : null;
        this.storeTypeId = inits.isInitialized("storeTypeId") ? new QStoreType(forProperty("storeTypeId")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

