package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Sales {

    @Id
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Store storeId;

    //	@Temporal(TemporalType.DATE)
    @CreatedDate
    @Column(name = "register_time", nullable = false)
    private LocalDate registerTime;

    @Column(name = "amount", nullable = false)
    private Integer amount;
}
