package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @Column(name = "id", nullable = false, updatable = false, length = 32)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Order orderId;

    @ManyToOne
    @JoinColumn(name = "id")
    private Item itemId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "amount")
    private int amount;

    @Column(name = "pickStatus")
    private boolean pickStatus;

    public static OrderItem of (Order orderId, Item itemId){
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setItemId(itemId);
        return orderItem;
    }

}
