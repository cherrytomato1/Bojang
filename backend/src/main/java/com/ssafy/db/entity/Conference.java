package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
public class Conference extends BaseEntity {
    int owner_id;
    int conference_category;
    Date call_start_time;
    Date call_end_time;
    String Thumbnail_url;
    String title;
    String description;
    Boolean is_active;
}
