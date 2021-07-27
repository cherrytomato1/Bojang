package com.ssafy.db.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id", nullable = false, updatable = false, length = 32)
    String id;

    @Column(name = "name", nullable = false, length = 10)
    String name;

    @Column(name = "zip_code", length = 5)
    Integer zipCode;

    @Column(name = "address", length = 200)
    String address;

    @Column(name = "address_detail", length = 200)
    String addressDetail;

    @Column(name = "account_number", length = 200)
    String accountNumber;

    //	@Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "register_time")
    LocalDateTime registerTime;

    @ManyToOne
    @JoinColumn(name = "id")
    BankType bankType;

    @ManyToOne
    @JoinColumn(name = "id")
    UserType userType;

}
