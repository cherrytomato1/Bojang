package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class User_conference extends BaseEntity{
    @ManyToOne
    Conference conference;
    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
}
