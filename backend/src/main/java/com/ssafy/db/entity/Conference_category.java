package com.ssafy.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Conference_category extends BaseEntity{
    String name;
}
