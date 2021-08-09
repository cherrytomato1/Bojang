package com.ssafy.common.util;

import com.ssafy.common.exception.handler.AuthException;
import com.ssafy.common.exception.handler.ResourceNotFoundException;
import com.ssafy.common.exception.handler.RestTemplateException;
import com.ssafy.db.entity.Store;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RestUtil {

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

	public Store getStoreByStoreId(String storeId) {
		String url = "http://localhost:8081/api/store/" + storeId;
		Store store;

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity entity = new HttpEntity<>(null);

		try {
			ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
				Map.class);
			Map<String, Object> responseBody = responseEntity.getBody();
			store = (Store) responseBody.get("store");
		} catch (final HttpClientErrorException ex) {
			if (ex.getStatusCode().is5xxServerError()) {
				throw new RestTemplateException(url, ex.getMessage(), ex.getStatusCode().value());
			}
			throw new ResourceNotFoundException("store", "storeId", ex.getMessage());
		}
		return store;
	}

//	public void patchStore(Store store) {
//		String url = "http://localhost:8081/api/store/";
//
//		RestTemplate restTemplate = new RestTemplate();
//		HttpEntity entity = new HttpEntity<>(store);
//
//
//		try {
//			ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, entity,
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
