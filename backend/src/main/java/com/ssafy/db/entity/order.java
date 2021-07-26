package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class order {

    @Id
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private int price;

    @ManyToOne
    private orderStatus orderStatus;

    @ManyToOne
    private itemType itemType;


}
