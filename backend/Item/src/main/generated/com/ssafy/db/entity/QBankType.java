package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBankType is a Querydsl query type for BankType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBankType extends EntityPathBase<BankType> {

    private static final long serialVersionUID = -1847089832L;

    public static final QBankType bankType = new QBankType("bankType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QBankType(String variable) {
        super(BankType.class, forVariable(variable));
    }

    public QBankType(Path<? extends BankType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBankType(PathMetadata metadata) {
        super(BankType.class, metadata);
    }

}

