package com.ssafy.common.util;

import com.ssafy.common.handler.AuthException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class GetUserIdUtil {


    public String getUserId(HttpHeaders httpHeaders) {
        String id = "";
        String url = "http://i5a508.p.ssafy.io:8080/api/user/userId";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entity = new HttpEntity<>(null,httpHeaders);
        try {
            ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET,entity,Map.class);
            Map<String,Object> map = (Map<String, Object>) responseEntity.getBody();
            id = (String) map.get("userId");
            return id;
        } catch (final HttpClientErrorException e){
           throw new AuthException(e.getMessage());
        }
    }
}
