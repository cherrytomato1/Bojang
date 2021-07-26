package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class orderItem {

    @Id
    private String id;

    @ManyToOne
    private order order;

    @ManyToOne
    private item item;

    private String comment;

    private int amount;

    private boolean pickStatus;

    public static orderItem of (order order, item item){
        orderItem orderItem = new orderItem();
        orderItem.setOrder(order);
        orderItem.setItem(item);
        return orderItem;
    }

}
