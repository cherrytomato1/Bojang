package com.ssafy.db.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Sales {

    @Id
    @GenericGenerator(name = "Id", strategy = "com.ssafy.db.util.IdGenerator")
    @GeneratedValue(generator = "Id")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    //	@Temporal(TemporalType.DATE)
    @CreatedDate
    @Column(name = "register_time", nullable = false)
    private LocalDate registerTime;

    @Column(name = "sum", nullable = false)
    private Integer sum;
}
