package com.ssafy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.db.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RestTemplateTest {

    @Autowired
    ObjectMapper objectMapper;
    @Test
    public void restApiTest(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        String url = "http://localhost:8080/api/v1/auth/login";
        Map<String,Object> map = new HashMap<>();
        map.put("id","ssafy_web");
        map.put("password","your_password");
        try {
            String param = objectMapper.writeValueAsString(map);
            HttpEntity entity = new HttpEntity(param,httpHeaders);
            RestTemplate rest = new RestTemplate();
            ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.POST,entity,String.class);
            System.out.println(responseEntity.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

}
