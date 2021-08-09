package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Billing {

    @Id
    @GenericGenerator(name = "id", strategy = "com.ssafy.db.util.IdGenerator")
    @GeneratedValue(generator = "id")
    private String id;

    private String orderInfoId;

    private String billingStatus;

    @CreatedDate
    private LocalDateTime registerTime;

}
