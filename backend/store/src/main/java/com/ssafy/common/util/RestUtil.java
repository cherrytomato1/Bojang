package com.ssafy.common.util;

import com.ssafy.common.exception.handler.AuthException;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RestUtil {
    final String LOCAL = "http://localhost";
    final String AWS = "http://i5a508.p.ssafy.io";
    final String CURRENT_SERVER_DOMAIN = AWS;

    final String AUTH_PORT = ":8085";

    public String getUserId(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization",token);
        String url = CURRENT_SERVER_DOMAIN + AUTH_PORT + "/api/user/id";
//        String url = AWS + AUTH_PORT + "/api/user/id";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entity = new HttpEntity<>(null,httpHeaders);
        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET,entity,Map.class);
            Map<String,String> map = responseEntity.getBody();
            return map.get("userId");
        } catch (final HttpClientErrorException e){
           throw new AuthException(e.getMessage());
        }
    }
}
