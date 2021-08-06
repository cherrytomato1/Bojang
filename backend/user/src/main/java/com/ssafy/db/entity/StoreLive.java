package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter @Setter
public class StoreLive {
	@Id
	private Long id;

	@ManyToOne
	private Store store;
}
