package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
public class Conference_history extends BaseEntity{
    int conferenceId;
    int userId;
    int action;
    Date insertedTime;
}
