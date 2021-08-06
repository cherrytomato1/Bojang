package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuthUser is a Querydsl query type for AuthUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAuthUser extends EntityPathBase<AuthUser> {

    private static final long serialVersionUID = 1444299605L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuthUser authUser = new QAuthUser("authUser");

    public final StringPath email = createString("email");

    public final BooleanPath emailVerified = createBoolean("emailVerified");

    public final StringPath id = createString("id");

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final EnumPath<com.ssafy.security.OAuthProvider> provider = createEnum("provider", com.ssafy.security.OAuthProvider.class);

    public final QUser user;

    public QAuthUser(String variable) {
        this(AuthUser.class, forVariable(variable), INITS);
    }

    public QAuthUser(Path<? extends AuthUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuthUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuthUser(PathMetadata metadata, PathInits inits) {
        this(AuthUser.class, metadata, inits);
    }

    public QAuthUser(Class<? extends AuthUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

