package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Conference extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
    @ManyToOne
    Conference_category conferenceCategory;
    @Temporal(TemporalType.TIMESTAMP)
    Date call_start_time;
    @Temporal(TemporalType.TIMESTAMP)
    Date call_end_time;
    String Thumbnail_url;
    String title;
    String description;
    Boolean is_active;
}
