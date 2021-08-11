package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private OrderInfo orderInfo;

    @ManyToOne
    private Item item;

    @Column(name = "comment")
    private String comment;

    @Column(name = "amount")
    private int amount;

    @Column(name = "pickStatus")
    private boolean pickStatus;

    public static OrderItem of (OrderInfo order, Item item){
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderInfo(order);
        orderItem.setItem(item);
        return orderItem;
    }

}
