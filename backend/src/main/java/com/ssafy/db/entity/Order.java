package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Order {

    @Id
    @Column(name = "id", nullable = false, updatable = false, length = 32)
    private String id;

    @CreatedDate
    @Column(name = "register_time")
    private LocalDateTime registerTime;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "id")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "id")
    private ItemType itemType;

    @ManyToOne
    @JoinColumn(name = "id")
    private User userId;


}
