package com.ssafy.db.entity;

import com.ssafy.common.auth.Role;
import lombok.*;

import javax.persistence.*;

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
