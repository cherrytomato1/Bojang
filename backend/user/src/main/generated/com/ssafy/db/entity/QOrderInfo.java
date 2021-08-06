package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderInfo is a Querydsl query type for OrderInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderInfo extends EntityPathBase<OrderInfo> {

    private static final long serialVersionUID = -19912806L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderInfo orderInfo = new QOrderInfo("orderInfo");

    public final StringPath id = createString("id");

    public final ListPath<OrderItem, QOrderItem> orderItemList = this.<OrderItem, QOrderItem>createList("orderItemList", OrderItem.class, QOrderItem.class, PathInits.DIRECT2);

    public final QOrderStatus orderStatus;

    public final QPayType payType;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> registerTime = createDateTime("registerTime", java.time.LocalDateTime.class);

    public final QUser user;

    public QOrderInfo(String variable) {
        this(OrderInfo.class, forVariable(variable), INITS);
    }

    public QOrderInfo(Path<? extends OrderInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderInfo(PathMetadata metadata, PathInits inits) {
        this(OrderInfo.class, metadata, inits);
    }

    public QOrderInfo(Class<? extends OrderInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderStatus = inits.isInitialized("orderStatus") ? new QOrderStatus(forProperty("orderStatus")) : null;
        this.payType = inits.isInitialized("payType") ? new QPayType(forProperty("payType")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

