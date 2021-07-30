package com.ssafy.db.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Store {
	@Id
	@GenericGenerator(name="Id",strategy = "com.ssafy.db.util.IdGenerator")
	@GeneratedValue(generator = "Id")
	private String id;

	@OneToOne
	@JoinColumn(name="id")
	private User user;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@Column(name = "section", nullable = false)
	private Integer section;

	@Column(name = "comment", length = 200)
	private String comment;

	@Column(name = "address", length = 200)
	private String address;

	@Column(name = "image")
	private String image;

	@ManyToOne
	private Market market;

	@ManyToOne
	private StoreType storeType;
}
