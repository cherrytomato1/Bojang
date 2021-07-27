package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBasket is a Querydsl query type for Basket
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBasket extends EntityPathBase<Basket> {

    private static final long serialVersionUID = 1218337800L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBasket basket = new QBasket("basket");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final StringPath id = createString("id");

    public final QItem itemId;

    public final QUser userId;

    public QBasket(String variable) {
        this(Basket.class, forVariable(variable), INITS);
    }

    public QBasket(Path<? extends Basket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBasket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBasket(PathMetadata metadata, PathInits inits) {
        this(Basket.class, metadata, inits);
    }

    public QBasket(Class<? extends Basket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.itemId = inits.isInitialized("itemId") ? new QItem(forProperty("itemId"), inits.get("itemId")) : null;
        this.userId = inits.isInitialized("userId") ? new QUser(forProperty("userId"), inits.get("userId")) : null;
    }

}

