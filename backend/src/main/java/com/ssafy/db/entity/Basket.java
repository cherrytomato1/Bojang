package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Basket {
    @Id
    @Column(name = "id", nullable = false, updatable = false, length = 32)
    private String id;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "id")
    private Item itemId;

    @ManyToOne
    @JoinColumn(name = "id")
    private User userId;



}
