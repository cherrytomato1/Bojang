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
	private String id;

	@Column(name = "name", nullable = false, length = 15)
	private String name;


	@Column(name = "phone_number", length = 15)
	private String phoneNumber;

	//판매자 전용
	@Column(name = "account_number", length = 200)
	private String accountNumber;

	//	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private LocalDateTime registerTime;

	//판매자 전용
	@ManyToOne
	private BankType bankType;

	@ManyToOne
	private UserType userType;

	//픽업 매니저 전용
	@ManyToOne
	private Market market;

}
