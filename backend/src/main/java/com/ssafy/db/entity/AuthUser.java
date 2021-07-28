package com.ssafy.db.entity;

import com.ssafy.common.auth.Role;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class AuthUser {
	@Id
	private Long id;

	@Column(nullable = false)
	private Role role;

	private String email;
}
