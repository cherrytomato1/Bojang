package com.ssafy.security.oauth2.user;

import static com.ssafy.security.OAuthProvider.google;
import static com.ssafy.security.OAuthProvider.kakao;

import com.ssafy.common.exception.handler.OAuth2AuthenticationProcessingException;
import java.util.Map;


public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if(registrationId.equalsIgnoreCase(kakao.toString())){
//            String nickName = (String)((Map<String,Object>)attributes.get("properties")).get("nickname");
//            System.out.println(attributes.get("name") + " nick name : " + nickName);
//            System.out.println(attributes.getClass());
            //unsupportedOperationException 발생 => unmodifiableMap 이기 때문
//            attributes.put("name", nickName);


//            System.out.println(attributes.get("name") + " nick name : " + nickName);
            return new KakaoOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
