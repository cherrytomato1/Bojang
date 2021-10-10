package com.ssafy.security.oauth2.user;

import static com.ssafy.security.OAuthProvider.google;
import static com.ssafy.security.OAuthProvider.kakao;

import com.ssafy.common.exception.handler.OAuth2AuthenticationProcessingException;
import java.util.Map;


public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        }
        if(registrationId.equalsIgnoreCase(kakao.toString())){
            return new KakaoOAuth2UserInfo(attributes);
        }
        throw new OAuth2AuthenticationProcessingException("Unsupported OAuth registration Id" + registrationId);
    }
}
