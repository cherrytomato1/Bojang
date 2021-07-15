package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser_conference is a Querydsl query type for User_conference
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser_conference extends EntityPathBase<User_conference> {

    private static final long serialVersionUID = 1147320846L;

    public static final QUser_conference user_conference = new QUser_conference("user_conference");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> conferenceId = createNumber("conferenceId", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QUser_conference(String variable) {
        super(User_conference.class, forVariable(variable));
    }

    public QUser_conference(Path<? extends User_conference> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser_conference(PathMetadata metadata) {
        super(User_conference.class, metadata);
    }

}

