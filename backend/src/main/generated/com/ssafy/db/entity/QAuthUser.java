package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuthUser is a Querydsl query type for AuthUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAuthUser extends EntityPathBase<AuthUser> {

    private static final long serialVersionUID = 1444299605L;

    public static final QAuthUser authUser = new QAuthUser("authUser");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.ssafy.common.auth.Role> role = createEnum("role", com.ssafy.common.auth.Role.class);

    public QAuthUser(String variable) {
        super(AuthUser.class, forVariable(variable));
    }

    public QAuthUser(Path<? extends AuthUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthUser(PathMetadata metadata) {
        super(AuthUser.class, metadata);
    }

}

