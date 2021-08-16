package com.ssafy.common.util;

import com.ssafy.common.exception.handler.AuthException;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RestUtil {


    public String getUserId(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization",token);
        String id = "";
        String url = "http://localhost:8085/api/user/id";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entity = new HttpEntity<>(null,httpHeaders);
        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET,entity,Map.class);
            Map<String,String> map =responseEntity.getBody();
            id = map.get("userId");
            return id;
        } catch (final HttpClientErrorException e){
           throw new AuthException(e.getMessage());
        }
    }
}
