package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPrice is a Querydsl query type for Price
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrice extends EntityPathBase<Price> {

    private static final long serialVersionUID = 468369095L;

    public static final QPrice price1 = new QPrice("price1");

    public final StringPath id = createString("id");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> registerTime = createDateTime("registerTime", java.time.LocalDateTime.class);

    public QPrice(String variable) {
        super(Price.class, forVariable(variable));
    }

    public QPrice(Path<? extends Price> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrice(PathMetadata metadata) {
        super(Price.class, metadata);
    }

}

