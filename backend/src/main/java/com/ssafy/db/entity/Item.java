package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @Column(name = "id", nullable = false, updatable = false, length = 32)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "on_sale")
    private boolean onSale;

    @CreatedDate
    @Column(name = "register_time")
    private LocalDateTime registerTime;

    @ManyToOne
//    @JoinColumn(name = "id")
    private Price price;


    @ManyToOne
//    @JoinColumn(name = "id")
    private ItemType itemType;


    @ManyToOne
//    @JoinColumn(name = "id")
    private Store store;


}
