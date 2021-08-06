package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class OrderList {

    @Id
    @GenericGenerator(name="Id",strategy = "com.ssafy.db.util.OrderIdGenerator")
    @GeneratedValue(generator = "Id")
    private String id;

    @CreatedDate
    private LocalDateTime registerTime;

    @Column(name = "price")
    private int price;

    @ManyToOne
    private OrderStatus orderStatus;

    @ManyToOne
    private PayType payType;

    @ManyToOne
    private User user;




}
