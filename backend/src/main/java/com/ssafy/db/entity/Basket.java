package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Basket {
    @Id
    @GenericGenerator(name="Id",strategy = "com.ssafy.db.util.IdGenerator")
    @GeneratedValue(generator = "Id")
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
