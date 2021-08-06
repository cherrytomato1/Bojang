package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Getter @Setter
public class BankType {
	@Id
	private  Long id;

	@Column(name = "name")
	private String name;


}
