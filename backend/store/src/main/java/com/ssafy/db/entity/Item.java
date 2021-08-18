package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Item {

	@Id
	@GenericGenerator(name = "Id", strategy = "com.ssafy.db.util.IdGenerator")
	@GeneratedValue(generator = "Id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "content")
	private String content;

	@Column(name = "image")
	private String image;

	@Column(name = "on_sale")
	private boolean onSale;

	private Long price;

	@CreatedDate
	@Column(name = "register_time")
	private LocalDateTime registerTime;

	@ManyToOne
	private ItemType itemType;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Store store;


}
