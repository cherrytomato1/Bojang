package com.ssafy.db.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Sales {

    @Id
    @Generated
    private Long id;

//    @Id
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false, insertable = false, updatable = false)
    private Store storeId;

    //	@Temporal(TemporalType.DATE)
    @CreatedDate
    @Column(name = "register_time", nullable = false, updatable = false)
    private LocalDate registerTime;

    @Column(name = "amount", nullable = false, updatable = false)
    private Integer amount;
}
