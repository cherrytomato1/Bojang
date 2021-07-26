package com.ssafy.db.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Store {
	@Id
	@Column(name="id", nullable = false, length = 32)
	String id;

	@OneToOne
	@JoinColumn(name="id")
	User user;

	@Column(name = "name", nullable = false, length = 20)
	String name;

	@Column(name = "section", nullable = false)
	Integer section;

	@Column(name = "comment", length = 200)
	String comment;

	@Column(name = "address", length = 200)
	String address;

	@Column(name = "image")
	String image;

	@ManyToOne
	@JoinColumn(name = "id")
	Market marketId;

	@ManyToOne
	@JoinColumn(name = "id")
	StoreType storeTypeId;
}
