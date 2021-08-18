package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemType is a Querydsl query type for ItemType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemType extends EntityPathBase<ItemType> {

    private static final long serialVersionUID = 1189533359L;

    public static final QItemType itemType = new QItemType("itemType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QItemType(String variable) {
        super(ItemType.class, forVariable(variable));
    }

    public QItemType(Path<? extends ItemType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemType(PathMetadata metadata) {
        super(ItemType.class, metadata);
    }

}

