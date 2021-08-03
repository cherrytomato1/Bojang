package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.security.OAuthProvider;
import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUser {

	@Id
	private String id;

	private String name;

	private String email;

	private String imageUrl;

	private Boolean emailVerified;

	@JsonIgnore
	private String password;

	private OAuthProvider provider;

	@OneToOne
	@NotNull
	private User user;
}
