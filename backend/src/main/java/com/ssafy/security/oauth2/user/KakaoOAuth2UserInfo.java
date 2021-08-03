package com.ssafy.security.oauth2.user;

import com.ssafy.common.exception.handler.OAuth2AuthenticationProcessingException;
import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {
    private final Map<String, Object> properties;

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
        if(attributes.get("properties") instanceof Map) {
            this.properties = (Map<String, Object>) attributes.get("properties");
        } else {
            throw new OAuth2AuthenticationProcessingException("Kakao properties are not available");
        }
    }

    //id가 integer로 attributes에 저장되어 있으므로 캐스팅하여 반환
    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    //name이 아니라 nickname으로 참조
    @Override
    public String getName() {
        return (String) properties.get("nickname");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }
}
