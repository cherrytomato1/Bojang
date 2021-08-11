package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class StoreType {
	@Id
	private Long id;

	private String name;
}
