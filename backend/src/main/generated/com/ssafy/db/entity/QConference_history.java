package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QConference_history is a Querydsl query type for Conference_history
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConference_history extends EntityPathBase<Conference_history> {

    private static final long serialVersionUID = -1135524941L;

    public static final QConference_history conference_history = new QConference_history("conference_history");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> action = createNumber("action", Integer.class);

    public final NumberPath<Integer> conferenceId = createNumber("conferenceId", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.util.Date> insertedTime = createDateTime("insertedTime", java.util.Date.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QConference_history(String variable) {
        super(Conference_history.class, forVariable(variable));
    }

    public QConference_history(Path<? extends Conference_history> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConference_history(PathMetadata metadata) {
        super(Conference_history.class, metadata);
    }

}

