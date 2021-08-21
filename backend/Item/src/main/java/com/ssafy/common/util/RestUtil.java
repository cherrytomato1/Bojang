package com.ssafy.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RestUtil {

	final ObjectMapper objectMapper;

	final String TOKEN_KEY = "Authorization";
	final String LOCAL = "http://localhost";
	final String AWS = "https://i5a508.p.ssafy.io";
	final String CURRENT_SERVER_DOMAIN = AWS;

	final String AUTH_PORT = ":8085";
	final String STORE_PORT = ":8081";
	final String BILLING_PORT = ":8083";

	public String getUserId(String token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(TOKEN_KEY, token);
		String url = CURRENT_SERVER_DOMAIN + AUTH_PORT + "/api/user/id";
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<HttpHeaders> entity = new HttpEntity<>(null, httpHeaders);
		try {
			ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
				Map.class);
			Map<String, String> map = responseEntity.getBody();
			return map.get("userId");
		} catch (final HttpClientErrorException e) {
			throw new AuthException(e.getMessage());
		}
	}

	public User getUserByToken(String token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(TOKEN_KEY, token);
//		String url = "http://localhost:8085/api/user";

		String url = CURRENT_SERVER_DOMAIN + AUTH_PORT + "/api/user";
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
//		String url = "http://localhost:8081/api/store/" + storeId;
		String url = CURRENT_SERVER_DOMAIN + STORE_PORT + "/api/store/" + storeId;
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
		httpHeaders.set(TOKEN_KEY, token);

//		String url = "http://localhost:8083/api/billing";
		String url = CURRENT_SERVER_DOMAIN + BILLING_PORT + "/api/bill";
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		paramMap.add("orderInfoId", orderInfoId);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(paramMap, httpHeaders);

		try {
			restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
//			Map<String, Object> responseBody = objectMapper.convertValue(responseEntity.getBody(),
//			Map.class);
		} catch (final HttpClientErrorException ex) {
			throw new RestTemplateException(url, ex.getMessage(), ex.getStatusCode().value());
		}
	}

	public void addStoreSalePrice(Long price, String storeId) {
//		String url = "http://localhost:8081/api/sales";
		String url = CURRENT_SERVER_DOMAIN + STORE_PORT + "/api/sales";
		HttpHeaders httpHeaders = new HttpHeaders();
		//json 객체로 지정
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		httpHeaders.add("Content-Type", "application/json");

		RestTemplate restTemplate = new RestTemplate();
//		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
		//google.gson.JsonObject
		JsonObject paramJSONObject = new JsonObject();
		//프로퍼티 추가
		paramJSONObject.addProperty("sum", price);
		paramJSONObject.addProperty("storeId", storeId);
//		paramMap.add("sum", price);
//		paramMap.add("storeId", storeId);
//		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(paramMap, httpHeaders);

		//json to string으로 전송
		HttpEntity<String> entity = new HttpEntity<>(paramJSONObject.toString(), httpHeaders);

		try {
			System.out.println(url);
			restTemplate.exchange(url, HttpMethod.PUT, entity, Map.class);
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
