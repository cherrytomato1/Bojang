package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Conference_history extends BaseEntity {
    @ManyToOne
    Conference conference;
    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
    int action;
    @Temporal(TemporalType.TIMESTAMP)
    Date insertedTime;
}
