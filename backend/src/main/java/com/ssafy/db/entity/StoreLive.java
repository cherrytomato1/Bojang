package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter
public class StoreLive {
	@Id
	@OneToOne
	@JoinColumn(name = "id")
	private Store storeId;
}
