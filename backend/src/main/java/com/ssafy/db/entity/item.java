package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class item {

    @Id
    private String id;

    private String storeId;

    private String name;

    private String content;

    private String image;

    private boolean onSale;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registerTime;


    @ManyToOne
    private price price;


    @ManyToOne
    private itemType itemType;





}
