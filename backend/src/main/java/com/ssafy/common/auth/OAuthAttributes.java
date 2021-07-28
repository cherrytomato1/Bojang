package com.ssafy.common.auth;

import com.ssafy.db.entity.AuthUser;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
	private Integer id;
	private String nameAttributeKey;
	private String name;
	private String email;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, Integer id) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.id = id;
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
		return of("id", attributes);
	}

	private static OAuthAttributes of(String userNameAttributeName, Map<String, Object> attributes) {
		// kakao는 kakao_account에 유저정보가 있다. (email)
		Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
		// kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
		Map<String, Object> profile = (Map<String, Object>) account.get("profile");
		return OAuthAttributes.builder()
					   .name((String) profile.get("name"))
//					   .email((String) account.get("email"))
					   .id((Integer)attributes.get("id"))
					   .attributes(attributes)
					   .nameAttributeKey(userNameAttributeName)
					   .build();
	}

	public AuthUser toEntity(){
		return AuthUser.builder()
					   .email(email)
					   .id(id.longValue())
					   .role(Role.GUEST) // 기본 권한 GUEST
					   .build();
	}
}
