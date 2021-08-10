package com.ssafy.db.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id
	@GenericGenerator(name = "userId", strategy = "com.ssafy.db.util.IdGenerator")
	@GeneratedValue(generator = "userId")
	private String id;

	@Column(name = "name", nullable = false, length = 10)
	private String name;

	@Column(name = "account_number", length = 200)
	private String accountNumber;

	//	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private LocalDateTime registerTime;

	@ManyToOne
	private BankType bankType;

	@ManyToOne
	private UserType userType;

	@ManyToOne
	private Market market;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<OrderInfo> orderInfoList = new ArrayList<>();

	public void addOrderInfo(OrderInfo orderInfo) {
		orderInfoList.add(orderInfo);
		orderInfo.setUser(this);
	}
}
