package com.ssafy.security.oauth2;


import com.ssafy.common.exception.handler.OAuth2AuthenticationProcessingException;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.security.OAuthProvider;
import com.ssafy.security.UserPrincipal;
import com.ssafy.security.oauth2.user.OAuth2UserInfo;
import com.ssafy.security.oauth2.user.OAuth2UserInfoFactory;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest)
		throws OAuth2AuthenticationException {

		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
		System.out.println("Token Type : " + oAuth2UserRequest.getAccessToken().getTokenType());
		System.out.println("Token Value : " + oAuth2UserRequest.getAccessToken().getTokenValue());
		//attribute 로그
		Map<String, Object> attributes = oAuth2User.getAttributes();
		attributes.forEach((key, value) -> System.out.println(key + " " + value));

		try {
			return processOAuth2User(oAuth2UserRequest, oAuth2User);
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			// Throwing an instance of AuthenticationException will trigger the
			// OAuth2AuthenticationFailureHandler
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
		}
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest,
		OAuth2User oAuth2User) {
		//OAuth2UserInfoFactory로부터 registration ID를 통해 유저 정보를 받아온다
		String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();

		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
			                                .getOAuth2UserInfo(registrationId,
				                                oAuth2User.getAttributes());

		Optional<User> userOptional = getUserByRegistration(oAuth2UserInfo, registrationId);

		User user;
		if (userOptional.isPresent()) {
			user = userOptional.get();
			if (!user.getProvider().equals(
				OAuthProvider.valueOf(registrationId))) {
				throw new OAuth2AuthenticationProcessingException(
					"Looks like you're signed up with " +
						user.getProvider() + " account. Please use your " + user.getProvider() +
						" account to login.");
			}
			user = updateExistingUser(user, oAuth2UserInfo, registrationId);
		} else {
			user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo, registrationId);
		}

		return UserPrincipal.create(user, oAuth2User.getAttributes(), registrationId);
	}

	private Optional<User> getUserByRegistration(OAuth2UserInfo oAuth2UserInfo,
		String registrationId) {
		//메일이 비어있을 경우, 메일이 제공되지 않았음을 예외처리
		if (registrationId.equalsIgnoreCase(OAuthProvider.google.toString())) {
			if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
				throw new OAuth2AuthenticationProcessingException(
					"Email not found from Google OAuth");
			}
			return userRepository.findByEmail(oAuth2UserInfo.getEmail());
		}

		if (registrationId.equalsIgnoreCase(OAuthProvider.kakao.toString())) {
			if (StringUtils.isEmpty(oAuth2UserInfo.getId())) {
				throw new OAuth2AuthenticationProcessingException(
					"Id not found from Kakao OAuth");
			}
			//authId로 가입된 유저 참조 없으면 Optional.empty
			return userRepository.findByAuthId(oAuth2UserInfo.getId());
		}
		throw new OAuth2AuthenticationProcessingException("invalid registrationId");
	}

	private User registerNewUser(OAuth2UserRequest oAuth2UserRequest,
		OAuth2UserInfo oAuth2UserInfo, String registrationId) {

		User user = new User();

		user.setProvider(
			OAuthProvider.valueOf(registrationId));
		user.setAuthId(oAuth2UserInfo.getId());
		user.setName(oAuth2UserInfo.getName());
		user.setEmail(oAuth2UserInfo.getEmail());
		user.setImageUrl(setImageUrl(oAuth2UserInfo, registrationId));
//		user.setId(oAuth2UserInfo.getId());
		return userRepository.save(user);
	}

	private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo,
		String registrationId) {

		existingUser.setName(oAuth2UserInfo.getName());
		existingUser.setImageUrl(setImageUrl(oAuth2UserInfo, registrationId));
		return userRepository.save(existingUser);
	}

	private String setImageUrl(OAuth2UserInfo oAuth2UserInfo, String registrationId) {

		System.out.println("kakaoImage" + registrationId);
		if (registrationId.equals("kakao")) {
			Optional<Map<String, Object>> kakaoAccount = Optional.ofNullable(
				(Map<String, Object>) oAuth2UserInfo.getAttributes()
					                      .get("kakao_account"));
			Optional<Map<String, String>> profile = Optional.ofNullable(
				(Map<String, String>) kakaoAccount.orElseThrow(
					() -> new OAuth2AuthenticationProcessingException("Kakao profile is not "
						                                                  + "found"))
					                      .get("profile"));
			return profile.orElseThrow(
				() -> new OAuth2AuthenticationProcessingException("Kakao profile is not found"))
				       .get("profile_image_url");
		}
		return oAuth2UserInfo.getImageUrl();
	}

}
