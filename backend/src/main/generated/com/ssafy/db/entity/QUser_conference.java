package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser_conference is a Querydsl query type for User_conference
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser_conference extends EntityPathBase<User_conference> {

    private static final long serialVersionUID = 1147320846L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser_conference user_conference = new QUser_conference("user_conference");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QConference conference;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QUser user;

    public QUser_conference(String variable) {
        this(User_conference.class, forVariable(variable), INITS);
    }

    public QUser_conference(Path<? extends User_conference> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser_conference(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser_conference(PathMetadata metadata, PathInits inits) {
        this(User_conference.class, metadata, inits);
    }

    public QUser_conference(Class<? extends User_conference> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conference = inits.isInitialized("conference") ? new QConference(forProperty("conference"), inits.get("conference")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

