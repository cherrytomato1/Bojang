package com.ssafy.db.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id
	@GenericGenerator(name = "userIdGenerator", strategy = "com.ssafy.db.util.IdGenerator")
	@GeneratedValue(generator = "userIdGenerator")
	String id;

	@Column(name = "name", nullable = false, length = 15)
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
	LocalDateTime registerTime;

	@Column(name = "phone_number", length = 15)
	String phoneNumber;

	@ManyToOne
	BankType bankType;

	@ManyToOne
	UserType userType;

}
