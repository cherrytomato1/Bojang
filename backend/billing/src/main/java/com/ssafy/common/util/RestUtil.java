package com.ssafy.common.util;

import com.ssafy.common.exception.handler.AuthException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RestUtil {

    private String baseUrl = "http://localhost:";


    public String getUserId(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);
        String id = "";
        String url = baseUrl + "8080/api/user/userId";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entity = new HttpEntity<>(null, httpHeaders);
        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            Map<String, String> map = responseEntity.getBody();
            id = map.get("userId");
            return id;
        } catch (final HttpClientErrorException e) {
            throw new AuthException(e.getMessage());
        }
    }

    public String postBilling(String orderInfoId) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("orderInfoId", orderInfoId);
        String url = baseUrl + "8083/api/billing";
        HttpEntity entity = new HttpEntity<>(map,null);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        Map<String, String> stringMap = responseEntity.getBody();
        String status = stringMap.get("message");
        return status;
    }


}
