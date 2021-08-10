package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPayType is a Querydsl query type for PayType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPayType extends EntityPathBase<PayType> {

    private static final long serialVersionUID = -1341209088L;

    public static final QPayType payType = new QPayType("payType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QPayType(String variable) {
        super(PayType.class, forVariable(variable));
    }

    public QPayType(Path<? extends PayType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPayType(PathMetadata metadata) {
        super(PayType.class, metadata);
    }

}

