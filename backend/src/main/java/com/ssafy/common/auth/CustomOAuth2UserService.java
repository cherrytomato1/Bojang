package com.ssafy.common.auth;

import com.ssafy.db.entity.AuthUser;
import com.ssafy.db.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

//	private final UserRepository userRepository;
	private final HttpSession httpSession;
	private final AuthUserRepository authUserRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		System.out.println("=========> loadUser");

		// OAuth2 서비스 id (구글, 카카오, 네이버)
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		// OAuth2 로그인 진행 시 키가 되는 필드 값(PK)
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

		// OAuth2UserService
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		AuthUser user = saveOrUpdate(attributes);
//		httpSession.setAttribute("user", new SessionUser(user));
		httpSession.setAttribute("user", user);

		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRole().getKey())),
				attributes.getAttributes(),
				attributes.getNameAttributeKey());
	}

	// 유저 생성 및 수정 서비스 로직
	private AuthUser saveOrUpdate(OAuthAttributes attributes){
//		AuthUser user = authUserRepository.findByEmail(attributes.getEmail())
////							.map(entity -> entity.update(attributes.getName()))
//							.orElse(attributes.toEntity());
		System.out.println(attributes.getId().longValue());
		AuthUser user = authUserRepository.findById(attributes.getId().longValue())
				.orElse(attributes.toEntity());
		return authUserRepository.save(user);
	}
}
