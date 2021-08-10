package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBilling is a Querydsl query type for Billing
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBilling extends EntityPathBase<Billing> {

    private static final long serialVersionUID = -663631751L;

    public static final QBilling billing = new QBilling("billing");

    public final StringPath billingStatus = createString("billingStatus");

    public final StringPath id = createString("id");

    public final StringPath orderInfoId = createString("orderInfoId");

    public final DateTimePath<java.time.LocalDateTime> registerTime = createDateTime("registerTime", java.time.LocalDateTime.class);

    public QBilling(String variable) {
        super(Billing.class, forVariable(variable));
    }

    public QBilling(Path<? extends Billing> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBilling(PathMetadata metadata) {
        super(Billing.class, metadata);
    }

}

