package com.ssafy.security;

import com.ssafy.db.entity.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserPrincipal implements OAuth2User, UserDetails {

	//tokenProvider에서 넣어둔 userID(generated)
	private String id;
	private String email;
	//unused
	private String password;
	//oauthprovider (unused)
	private OAuthProvider oAuthProvider;
	private Collection<? extends GrantedAuthority> authorities;
	private Map<String, Object> attributes;

	public UserPrincipal(String id, String email, String password,
		Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrincipal create(AuthUser authUser) {
		List<GrantedAuthority> authorities = Collections.
			singletonList(new SimpleGrantedAuthority("ROLE_USER"));

		return new UserPrincipal(
			authUser.getId(),
			authUser.getEmail(),
			authUser.getPassword(),
			authorities
		);
	}

	public static UserPrincipal create(AuthUser authUser, Map<String, Object> attributes,
		String registrationId) {
		UserPrincipal userPrincipal = UserPrincipal.create(authUser);
		userPrincipal.setAttributes(attributes);
		userPrincipal.oAuthProvider = OAuthProvider.valueOf(registrationId);
		return userPrincipal;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	/*
		OAuth2Users 인터페이스의 getPassword 구현체
		미사용 데이터로 null 반환
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/*
		UserDetails 에서 userName 을 구분하기 위해 오버라이딩한 메서드
		유저 인증정보를 사용할 유저 아이디를 반환
	 */
	@Override
	public String getUsername() {
		//principal은 provider에 따라 다르게 발급
//		if (this.oAuthProvider == OAuthProvider.google) {
//			return email;
//		} else if (this.oAuthProvider == OAuthProvider.kakao) {
//			return id;
//		}
//		return null;
//		return getName();
		return id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getName() {
		return String.valueOf(id);
	}
}
