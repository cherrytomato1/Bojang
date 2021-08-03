package com.ssafy.security.oauth2;


import com.ssafy.common.exception.handler.OAuth2AuthenticationProcessingException;
import com.ssafy.db.entity.AuthUser;
import com.ssafy.db.repository.AuthUserRepository;
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

	private final AuthUserRepository authUserRepository;

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

		Optional<AuthUser> userOptional = getUserByRegistration(oAuth2UserInfo, registrationId);

		AuthUser authUser;
		if (userOptional.isPresent()) {
			authUser = userOptional.get();
			if (!authUser.getProvider().equals(
				OAuthProvider.valueOf(registrationId))) {
				throw new OAuth2AuthenticationProcessingException(
					"Looks like you're signed up with " +
						authUser.getProvider() + " account. Please use your " + authUser.getProvider() +
						" account to login.");
			}
			authUser = updateExistingUser(authUser, oAuth2UserInfo, registrationId);
		} else {
			authUser = registerNewUser(oAuth2UserRequest, oAuth2UserInfo, registrationId);
		}

		return UserPrincipal.create(authUser, oAuth2User.getAttributes(), registrationId);
	}

	private Optional<AuthUser> getUserByRegistration(OAuth2UserInfo oAuth2UserInfo,
		String registrationId) {
		//메일이 비어있을 경우, 메일이 제공되지 않았음을 예외처리
		if (registrationId.equalsIgnoreCase(OAuthProvider.google.toString())) {
			if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
				throw new OAuth2AuthenticationProcessingException(
					"Email not found from Google OAuth");
			}
			return authUserRepository.findByEmail(oAuth2UserInfo.getEmail());
		}

		if (registrationId.equalsIgnoreCase(OAuthProvider.kakao.toString())) {
			if (StringUtils.isEmpty(oAuth2UserInfo.getId())) {
				throw new OAuth2AuthenticationProcessingException(
					"Id not found from Kakao OAuth");
			}
			//authId로 가입된 유저 참조 없으면 Optional.empty
			return authUserRepository.findByAuthId(oAuth2UserInfo.getId());
		}
		throw new OAuth2AuthenticationProcessingException("invalid registrationId");
	}

	private AuthUser registerNewUser(OAuth2UserRequest oAuth2UserRequest,
		OAuth2UserInfo oAuth2UserInfo, String registrationId) {

		AuthUser authUser = new AuthUser();

		authUser.setProvider(
			OAuthProvider.valueOf(registrationId));
		authUser.setAuthId(oAuth2UserInfo.getId());
		authUser.setName(oAuth2UserInfo.getName());
		authUser.setEmail(oAuth2UserInfo.getEmail());
		authUser.setImageUrl(setImageUrl(oAuth2UserInfo, registrationId));
//		user.setId(oAuth2UserInfo.getId());
		return authUserRepository.save(authUser);
	}

	private AuthUser updateExistingUser(AuthUser existingAuthUser, OAuth2UserInfo oAuth2UserInfo,
		String registrationId) {

		existingAuthUser.setName(oAuth2UserInfo.getName());
		existingAuthUser.setImageUrl(setImageUrl(oAuth2UserInfo, registrationId));
		return authUserRepository.save(existingAuthUser);
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
