package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class StoreLive {
	@Id
	private Long id;

	@ManyToOne
	private Store store;
}
