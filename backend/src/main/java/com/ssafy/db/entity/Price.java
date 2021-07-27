package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Price {

    @Id
    @Column(name = "id", nullable = false, updatable = false, length = 32)
    private String id;

    @Column(name = "price")
    private int price;

    @CreatedDate
    @Column(name = "register_time")
    private LocalDateTime registerTime;
}
