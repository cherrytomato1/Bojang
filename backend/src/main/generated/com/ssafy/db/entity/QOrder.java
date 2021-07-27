package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 467440844L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final StringPath id = createString("id");

    public final QItemType itemType;

    public final QOrderStatus orderStatus;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> registerTime = createDateTime("registerTime", java.time.LocalDateTime.class);

    public final QUser userId;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.itemType = inits.isInitialized("itemType") ? new QItemType(forProperty("itemType")) : null;
        this.orderStatus = inits.isInitialized("orderStatus") ? new QOrderStatus(forProperty("orderStatus")) : null;
        this.userId = inits.isInitialized("userId") ? new QUser(forProperty("userId"), inits.get("userId")) : null;
    }

}

