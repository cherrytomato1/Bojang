package com.ssafy.db.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Store {
	@Id
	@GenericGenerator(name="Id",strategy = "com.ssafy.db.util.IdGenerator")
	@GeneratedValue(generator = "Id")
	private String id;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne(cascade = CascadeType.ALL)
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

	@OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
	private List<Item> itemList = new ArrayList<>();

	public void addItem(Item item){
		itemList.add(item);
		item.setStore(this);
	}

}
