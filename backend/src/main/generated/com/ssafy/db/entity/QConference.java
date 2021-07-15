package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QConference is a Querydsl query type for Conference
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConference extends EntityPathBase<Conference> {

    private static final long serialVersionUID = -625543586L;

    public static final QConference conference = new QConference("conference");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.util.Date> call_end_time = createDateTime("call_end_time", java.util.Date.class);

    public final DateTimePath<java.util.Date> call_start_time = createDateTime("call_start_time", java.util.Date.class);

    public final NumberPath<Integer> conference_category = createNumber("conference_category", Integer.class);

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final BooleanPath is_active = createBoolean("is_active");

    public final NumberPath<Integer> owner_id = createNumber("owner_id", Integer.class);

    public final StringPath Thumbnail_url = createString("Thumbnail_url");

    public final StringPath title = createString("title");

    public QConference(String variable) {
        super(Conference.class, forVariable(variable));
    }

    public QConference(Path<? extends Conference> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConference(PathMetadata metadata) {
        super(Conference.class, metadata);
    }

}

