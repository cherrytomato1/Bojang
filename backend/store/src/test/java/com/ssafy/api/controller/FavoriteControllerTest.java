package com.ssafy.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import com.ssafy.db.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FavoriteControllerTest {

    @Test
    public void getUserId(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODQwMjY0NDkwIiwianRpIjoiUzQ2ZmQ3Y2NmNDgzZSIsImlhdCI6MTYyODM0Njk1NiwiZXhwIjoxNjI4NDA3NDM2fQ.q3um2BAJ3QZFkymI628sBI9U-iAZJ680wW6m3fvhsuZ_3gPlOe-ivzKqj8bMnbJJuHtD2GT_rVvXPDnxiXxnGA");

        HttpEntity<User> entity = new HttpEntity<>(new User(),httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> responseEntity = restTemplate.exchange("http://localhost:8080/api/user/userId", HttpMethod.GET,entity,User.class);


        User user = responseEntity.getBody();

        System.out.println(user.getId());
    }
}