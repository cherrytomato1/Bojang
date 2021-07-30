package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class OrderList {

    @Id
    @GenericGenerator(name="Id",strategy = "com.ssafy.db.util.IdGenerator")
    @GeneratedValue(generator = "Id")
    private String id;

    @CreatedDate
    @Column(name = "register_time")
    private LocalDateTime registerTime;

    @Column(name = "price")
    private int price;

    @ManyToOne
    private OrderStatus orderStatus;

    @ManyToOne
    private ItemType itemType;

    @ManyToOne
    private User user;


}
