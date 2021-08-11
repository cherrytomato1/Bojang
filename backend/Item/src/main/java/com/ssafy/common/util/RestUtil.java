package com.ssafy.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.exception.handler.RestTemplateException;
import com.ssafy.db.entity.Store;
import com.ssafy.db.entity.User;
import java.net.ConnectException;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RestUtil {

	final ObjectMapper objectMapper;

	public String getUserId(String token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", token);
		String id;
		String url = "http://localhost:8080/api/user/id";
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<HttpHeaders> entity = new HttpEntity<>(null, httpHeaders);
		try {
			ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
				Map.class);
			Map<String, String> map = responseEntity.getBody();
			id = map.get("userId");
			return id;
		} catch (final HttpClientErrorException e) {
			throw new AuthException(e.getMessage());
		}
	}

	public User getUserByToken(String token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", token);
		String url = "http://localhost:8080/api/user";

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<HttpHeaders> entity = new HttpEntity<>(null, httpHeaders);
		User user;
		try {
			ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
				Map.class);
			Map<String, Object> responseBody = objectMapper.convertValue(responseEntity.getBody(),
				Map.class);
			user = objectMapper.convertValue(responseBody.get("user"), User.class);
		} catch (final HttpClientErrorException ex) {
			if (ex.getStatusCode().is5xxServerError()) {
				throw new RestTemplateException(url, ex.getMessage(), ex.getStatusCode().value());
			}
			throw new ResourceNotFoundException("user", "token", ex.getMessage());
		} catch (Exception ex) {
			throw new RestTemplateException(url, ex.getMessage(), 500);
		}
		return user;
	}

	public Store getStoreByStoreId(String storeId) {
		String url = "http://localhost:8081/api/store/" + storeId;
		Store store;

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity entity = new HttpEntity<>(null);

		try {
			ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
				Map.class);
//			Map<String, Object> responseBody = responseEntity.getBody();
			Map<String, Object> responseBody = objectMapper.convertValue(responseEntity.getBody(),
				Map.class);
			store = objectMapper.convertValue(responseBody.get("store"), Store.class);

		} catch (final HttpClientErrorException ex) {
			if (ex.getStatusCode().is5xxServerError()) {
				throw new RestTemplateException(url, ex.getMessage(), ex.getStatusCode().value());
			}
			throw new ResourceNotFoundException("store", "storeId", ex.getMessage());
		} catch (Exception ex) {
			throw new RestTemplateException(url, ex.getMessage(), 500);
		}
		return store;
	}

	public void sendBillingRequestByOrderInfoId(String orderInfoId, String token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", token);

		String url = "http://localhost:8083/api/billing/";
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("orderInfoId", orderInfoId);
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(paramMap, httpHeaders);

		try {
			restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
//			Map<String, Object> responseBody = objectMapper.convertValue(responseEntity.getBody(),
//			Map.class);
		} catch (final HttpClientErrorException ex) {
			throw new RestTemplateException(url, ex.getMessage(), ex.getStatusCode().value());
		}
	}

//	public void patchStore(Store store) {
//		String url = "http://localhost:8081/api/store/";
//
//		RestTemplate restTemplate = new RestTemplate();
//		HttpEntity entity = new HttpEntity<>(store);
//
//
//		try {
//			ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH,
//			entity,
//				Map.class);
//
//		} catch (final HttpClientErrorException ex) {
//			if (ex.getStatusCode().is5xxServerError()) {
//				throw new RestTemplateException(url, ex.getMessage(), ex.getStatusCode().value());
//			}
//			throw new ResourceNotFoundException("store", "storeId", ex.getMessage());
//		}
//	}
}
