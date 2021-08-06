package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStoreType is a Querydsl query type for StoreType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStoreType extends EntityPathBase<StoreType> {

    private static final long serialVersionUID = 2063974969L;

    public static final QStoreType storeType = new QStoreType("storeType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QStoreType(String variable) {
        super(StoreType.class, forVariable(variable));
    }

    public QStoreType(Path<? extends StoreType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStoreType(PathMetadata metadata) {
        super(StoreType.class, metadata);
    }

}

