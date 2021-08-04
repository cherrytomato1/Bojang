package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserType is a Querydsl query type for UserType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserType extends EntityPathBase<UserType> {

    private static final long serialVersionUID = -254465177L;

    public static final QUserType userType = new QUserType("userType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QUserType(String variable) {
        super(UserType.class, forVariable(variable));
    }

    public QUserType(Path<? extends UserType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserType(PathMetadata metadata) {
        super(UserType.class, metadata);
    }

}

