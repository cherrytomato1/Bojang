package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QConference_category is a Querydsl query type for Conference_category
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConference_category extends EntityPathBase<Conference_category> {

    private static final long serialVersionUID = 538788287L;

    public static final QConference_category conference_category = new QConference_category("conference_category");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public QConference_category(String variable) {
        super(Conference_category.class, forVariable(variable));
    }

    public QConference_category(Path<? extends Conference_category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConference_category(PathMetadata metadata) {
        super(Conference_category.class, metadata);
    }

}

