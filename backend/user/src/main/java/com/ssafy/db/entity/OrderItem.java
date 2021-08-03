package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GenericGenerator(name="Id",strategy = "com.ssafy.db.util.IdGenerator")
    @GeneratedValue(generator = "Id")
    private String id;

    @ManyToOne
    private OrderList order;

    @ManyToOne
    private Item item;

    @Column(name = "comment")
    private String comment;

    @Column(name = "amount")
    private int amount;

    @Column(name = "pickStatus")
    private boolean pickStatus;

    public static OrderItem of (OrderList order, Item item){
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setItem(item);
        return orderItem;
    }

}
