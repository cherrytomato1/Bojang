package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConference_history is a Querydsl query type for Conference_history
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConference_history extends EntityPathBase<Conference_history> {

    private static final long serialVersionUID = -1135524941L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConference_history conference_history = new QConference_history("conference_history");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> action = createNumber("action", Integer.class);

    public final QConference conference;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.util.Date> insertedTime = createDateTime("insertedTime", java.util.Date.class);

    public final QUser user;

    public QConference_history(String variable) {
        this(Conference_history.class, forVariable(variable), INITS);
    }

    public QConference_history(Path<? extends Conference_history> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConference_history(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConference_history(PathMetadata metadata, PathInits inits) {
        this(Conference_history.class, metadata, inits);
    }

    public QConference_history(Class<? extends Conference_history> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conference = inits.isInitialized("conference") ? new QConference(forProperty("conference"), inits.get("conference")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

