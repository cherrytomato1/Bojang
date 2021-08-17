package com.ssafy.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.db.entity.Store;
import com.ssafy.db.entity.User;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class RestUtilTest {

	@Autowired
	RestUtil restUtil;

	@Test
	public void 스토어아이디로_스토어를_받아온다() {
		//심지어 이거 원래 되는건데
		Store store = restUtil.getStoreByStoreId("Sa926f12a1a24");
	}

	@Test
	public void 스토어아이디와_sum으로_sale을_업데이트한다() {
		restUtil.addStoreSalePrice(1111L, "Sa926f12a1a24");
	}

	@Test
	public void 토큰으로_유저를_받아온다() {
		User user = restUtil.getUserByToken("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODIyNDk3NjY1IiwianRpIjoiUzc4MGJhZTIwM2ZlOSIsImlhdCI6MTYyOTEwODIzMCwiZXhwIjoxNjI5MTY4NzEwfQ.tdLyOVrxkGL5jdhwWEM9og1s30SPkFeEHdL7Ov3ZD5ICt3YsocfmOFIMX9DxlKGrDku-yYcMI95Zrc7u5kTM7w");
		System.out.println(user.getId());

	}


}