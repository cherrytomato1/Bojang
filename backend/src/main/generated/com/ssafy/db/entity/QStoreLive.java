package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreLive is a Querydsl query type for StoreLive
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStoreLive extends EntityPathBase<StoreLive> {

    private static final long serialVersionUID = 2063721451L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreLive storeLive = new QStoreLive("storeLive");

    public final QStore storeId;

    public QStoreLive(String variable) {
        this(StoreLive.class, forVariable(variable), INITS);
    }

    public QStoreLive(Path<? extends StoreLive> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreLive(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreLive(PathMetadata metadata, PathInits inits) {
        this(StoreLive.class, metadata, inits);
    }

    public QStoreLive(Class<? extends StoreLive> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.storeId = inits.isInitialized("storeId") ? new QStore(forProperty("storeId"), inits.get("storeId")) : null;
    }

}

